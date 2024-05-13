// scripts/main.js
document.addEventListener('DOMContentLoaded', function () {
    const memoryForm = document.getElementById('memoryForm');
    const memoriesSection = document.getElementById('memories');

    memoryForm.addEventListener('submit', function (event) {
        event.preventDefault();
        addMemory();
    });

    // Fetch memories from the backend when the page loads
    fetchMemories();

    function fetchMemories() {
        fetch('/api/memories')
            .then(response => response.json())
            .then(memories => displayMemories(memories))
            .catch(error => console.error('Error fetching memories:', error));
    }

    function displayMemories(memories) {
        memoriesSection.innerHTML = '';
        memories.forEach(memory => {
            const memoryCard = document.createElement('div');
            memoryCard.classList.add('memory-card');
            memoryCard.innerHTML = `
                <h2>${memory.title}</h2>
                <p>Date: ${memory.date}</p>
                <p>Location: ${memory.location}</p>
                <p>${memory.content}</p>
            `;
            memoriesSection.appendChild(memoryCard);
        });
    }

    function addMemory() {
        const formData = new FormData(memoryForm);
        const newMemory = {
            title: formData.get('title'),
            date: formData.get('date'),
            location: formData.get('location'),
            content: formData.get('content'),
        };

        // Make a POST request to add the new memory
        fetch('/api/memories', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newMemory),
        })
            .then(response => response.json())
            .then(createdMemory => {
                // Fetch memories again after adding a new memory
                fetchMemories();

                // Clear the form
                memoryForm.reset();
            })
            .catch(error => console.error('Error adding memory:', error));
    }
});
