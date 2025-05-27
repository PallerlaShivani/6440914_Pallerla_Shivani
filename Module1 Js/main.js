// Hey there! This script makes our community portal lively.

// Let's make sure our code runs only when the whole page is ready.
window.onload = function() {
    console.log("Community Portal is fully awake and ready!");

    // A little welcome message for the user, nicely fading in and out.
    const welcomeBubble = document.createElement('div');
    welcomeBubble.id = 'load-greeting';
    welcomeBubble.textContent = "Page loaded! Welcome aboard!";
    welcomeBubble.style.cssText = "position: fixed; top: 20px; right: 20px; background-color: #5cb85c; color: white; padding: 12px 20px; border-radius: 8px; z-index: 9999; opacity: 0; transition: opacity 0.6s ease; font-weight: 500;";
    document.body.appendChild(welcomeBubble);

    // Make it appear...
    setTimeout(() => {
        welcomeBubble.style.opacity = '1';
    }, 200);

    // ...and then disappear gracefully.
    setTimeout(() => {
        welcomeBubble.style.opacity = '0';
        setTimeout(() => welcomeBubble.remove(), 700); // Remove after fading out
    }, 3500);

    // Let's see if we remember their preferences from last time.
    loadMyPreferences();

    // Go fetch some events to show!
    fetchUpcomingEvents();
};

// --- Our Event Data & Basic Operations ---

// Keeping track of event details. Using 'const' because this list won't be reassigned.
const allEvents = [
    { id: 1, name: "Farmers Market Fun", date: "2024-06-15", spots: 100, type: "food", status: "upcoming" },
    { id: 2, name: "Summer Art Show", date: "2024-07-01", spots: 0, type: "art", status: "full" },
    { id: 3, name: "Neighborhood Clean-up", date: "2024-05-20", spots: 50, type: "community", status: "past" },
    { id: 4, name: "Intro to AI Workshop", date: "2024-08-10", spots: 30, type: "learning", status: "upcoming" },
    { id: 5, name: "Outdoor Yoga Session", date: "2024-09-05", spots: 20, type: "wellness", status: "upcoming" }
];

// This function takes our event list and shows them on the page.
function displayEventsOnPage(eventsToShow) {
    const eventContainer = document.getElementById('event-list-container');
    eventContainer.innerHTML = '<h3>Freshly Added Events</h3>'; // Clear previous content

    const eventListDiv = document.createElement('div'); // Using a div for event cards
    eventListDiv.className = 'event-cards-grid'; // We can style this with CSS Grid later if we want

    // Loop through each event we want to show
    eventsToShow.forEach(event => {
        const eventDateObj = new Date(event.date);
        const today = new Date();
        today.setHours(0, 0, 0, 0); // Make sure we compare dates without time

        // Only show events that are upcoming and have spots!
        if (eventDateObj >= today && event.spots > 0) {
            const eventCard = document.createElement('div');
            eventCard.className = 'event-card'; // Styling for individual event cards

            // Grab details using a neat trick called destructuring (Task 10)
            const { name, date, spots, type } = event;

            eventCard.innerHTML = `
                <h4>${name}</h4>
                <p>When: ${date}</p>
                <p>Type: ${type}</p>
                <p>Spots Left: ${spots > 0 ? spots : 'Full!'}</p>
                <button onclick="registerForEvent(${event.id})" class="join-button">Join Now</button>
            `;
            eventListDiv.appendChild(eventCard);
        } else {
            console.log(`Skipping "${event.name}" - it's either past or all booked up.`);
        }
    });
    eventContainer.appendChild(eventListDiv);

    if (eventsToShow.length === 0) {
        eventContainer.innerHTML += '<p>No new exciting events right now. Check back soon!</p>';
    }
}

// Initial display when the page loads
displayEventsOnPage(allEvents);

// --- Functions for Interactivity ---

// A simple tracker for registrations per category, kept private with a closure.
function createCategoryTracker() {
    const registrationsPerType = {}; // This variable is only accessible here!

    return function(eventType) {
        if (registrationsPerType[eventType]) {
            registrationsPerType[eventType]++;
        } else {
            registrationsPerType[eventType] = 1;
        }
        console.log(`We now have ${registrationsPerType[eventType]} sign-ups for "${eventType}" events.`);
    };
}
const trackRegistrations = createCategoryTracker(); // Get our special tracking function

// Function to handle someone signing up for an event
function registerForEvent(eventId) {
    try {
        const chosenEvent = allEvents.find(event => event.id === eventId);
        if (!chosenEvent) {
            throw new Error("Hmm, couldn't find that event.");
        }
        if (chosenEvent.spots <= 0) {
            throw new Error("Oh no, this event is all booked up!");
        }

        chosenEvent.spots--; // Decrease available spots
        trackRegistrations(chosenEvent.type); // Track it!
        console.log(`Awesome! You're in for "${chosenEvent.name}". Spots left: ${chosenEvent.spots}`);
        displayEventsOnPage(allEvents); // Refresh the event list

        // Give the user a quick message on the page
        const statusMsg = document.getElementById('registration-status-message');
        if (statusMsg) {
            statusMsg.textContent = `Woohoo! You're registered for "${chosenEvent.name}"! (${chosenEvent.spots} spots remain)`;
            statusMsg.style.color = '#28a745'; // Green for success
            statusMsg.style.backgroundColor = '#e6ffed';
            setTimeout(() => {
                statusMsg.textContent = '';
                statusMsg.style.backgroundColor = 'transparent';
            }, 3000); // Clear message after 3 seconds
        }

    } catch (error) {
        console.error("Oops, registration failed:", error.message);
        const statusMsg = document.getElementById('registration-status-message');
        if (statusMsg) {
            statusMsg.textContent = `Couldn't register: ${error.message}`;
            statusMsg.style.color = '#e63946'; // Red for error
            statusMsg.style.backgroundColor = '#ffe6e6';
            setTimeout(() => {
                statusMsg.textContent = '';
                statusMsg.style.backgroundColor = 'transparent';
            }, 3000);
        }
    }
}

// Higher-order function: This one helps us filter our event list based on any rule we give it.
function findEventsThatMatch(filterFunction) {
    return allEvents.filter(filterFunction);
}

// Example: Filter events by category using the higher-order function
function filterEventsByType(typeToFilter) {
    const filtered = findEventsThatMatch(event =>
        event.type === typeToFilter &&
        new Date(event.date) >= new Date().setHours(0,0,0,0) &&
        event.spots > 0
    );
    displayEventsOnPage(filtered);
    console.log(`Showing events of type: ${typeToFilter}`);
}

// --- Working with Forms ---

const registrationForm = document.getElementById('registrationForm');
const registrationStatus = document.getElementById('registration-status-message');

// When the form is submitted
registrationForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Stop the page from reloading, we'll handle it with JS!

    // Grab all the info from the form fields
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const eventDate = document.getElementById('eventDate').value;
    const eventType = document.getElementById('eventType').value;
    const message = document.getElementById('message').value;

    // A simple way to check if everything looks good
    let formOkay = true;

    if (name.trim() === '') {
        showFieldFeedback('name', 'Please tell us your name!');
        formOkay = false;
    } else {
        clearFieldFeedback('name');
    }

    if (!isValidEmail(email)) {
        showFieldFeedback('email', 'Looks like that email is not quite right.');
        formOkay = false;
    } else {
        clearFieldFeedback('email');
    }

    if (!isValidPhoneFormat(phone)) {
        showFieldFeedback('phone', 'Phone should be like 123-456-7890.');
        formOkay = false;
    } else {
        clearFieldFeedback('phone');
    }

    if (eventType === '') {
        showFieldFeedback('eventType', 'Don\'t forget to pick an event type!');
        formOkay = false;
    } else {
        clearFieldFeedback('eventType');
    }

    if (formOkay) {
        console.log("Form looks good! Sending data:", { name, email, phone, eventDate, eventType, message });
        // Send this info to our pretend server
        sendRegistrationToServer({ name, email, phone, eventDate, eventType, message });
    } else {
        console.log("Hmm, there are some issues with the form. Please fix them.");
        registrationStatus.textContent = "Please fix the highlighted fields.";
        registrationStatus.style.color = '#e63946';
        registrationStatus.style.backgroundColor = '#ffe6e6';
        setTimeout(() => {
            registrationStatus.textContent = '';
            registrationStatus.style.backgroundColor = 'transparent';
        }, 4000);
    }
});

// Helper for showing messages right next to a form field
function showFieldFeedback(elementId, message) {
    const inputElement = document.getElementById(elementId);
    let errorSpan = inputElement.nextElementSibling; // Get the next element (should be our error span)
    if (!errorSpan || !errorSpan.classList.contains('error-feedback')) {
        errorSpan = document.createElement('span');
        errorSpan.className = 'error-feedback';
        inputElement.parentNode.insertBefore(errorSpan, inputElement.nextSibling);
    }
    errorSpan.textContent = message;
}

// Helper for clearing messages next to a form field
function clearFieldFeedback(elementId) {
    const inputElement = document.getElementById(elementId);
    const errorSpan = inputElement.nextElementSibling;
    if (errorSpan && errorSpan.classList.contains('error-feedback')) {
        errorSpan.textContent = '';
    }
}

// A simple email checker
function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

// Checks if the phone number is in the 123-456-7890 format
function isValidPhoneFormat(phone) {
    return /^\d{3}-\d{3}-\d{4}$/.test(phone);
}

// This runs when the phone field loses focus (onblur)
function checkPhoneFormat() {
    const phoneInput = document.getElementById('phone');
    if (!isValidPhoneFormat(phoneInput.value) && phoneInput.value.trim() !== '') {
        showFieldFeedback('phone', 'Just need 3-3-4 digits like 123-456-7890.');
    } else {
        clearFieldFeedback('phone');
    }
}

// This updates the estimated cost when you pick an event type (onchange)
function updateEventCost() {
    const typePicker = document.getElementById('eventType');
    const costDisplay = document.getElementById('eventCostOutput');
    let estimatedCost = 0;
    switch (typePicker.value) {
        case 'workshop':
            estimatedCost = 25;
            break;
        case 'seminar':
            estimatedCost = 50;
            break;
        case 'webinar':
            estimatedCost = 0;
            break;
        default:
            estimatedCost = 0;
    }
    costDisplay.textContent = `$${estimatedCost}`;
}

// --- Simulating Backend Communication (AJAX & Fetch API) ---

// Pretend we're sending registration data to a server
async function sendRegistrationToServer(formData) {
    const outputArea = document.getElementById('registration-status-message');
    outputArea.textContent = 'Sending your info...';
    outputArea.style.color = '#555';
    outputArea.style.backgroundColor = '#f0f0f0';

    try {
        // Let's pretend there's a network delay and a random chance of success
        const mockResponse = await new Promise(resolve => setTimeout(() => {
            const didSucceed = Math.random() > 0.2; // 80% chance of success
            if (didSucceed) {
                resolve({ ok: true, status: 200, json: () => Promise.resolve({ message: "Registration confirmed! See you there!" }) });
            } else {
                resolve({ ok: false, status: 500, statusText: "Oops, server hiccup!", json: () => Promise.resolve({ message: "Something went wrong on our end. Please try again." }) });
            }
        }, 2500)); // Simulates a 2.5 second network wait

        if (mockResponse.ok) {
            const serverResult = await mockResponse.json();
            outputArea.textContent = `Success! ${serverResult.message}`;
            outputArea.style.color = '#28a745';
            outputArea.style.backgroundColor = '#e6ffed';
            registrationForm.reset(); // Clear the form
        } else {
            const errorDetails = await mockResponse.json();
            outputArea.textContent = `Failed: ${errorDetails.message || mockResponse.statusText}`;
            outputArea.style.color = '#e63946';
            outputArea.style.backgroundColor = '#ffe6e6';
        }
    } catch (error) {
        console.error('Network problem during registration:', error);
        outputArea.textContent = `Couldn't connect. Check your internet? ${error.message}`;
        outputArea.style.color = '#e63946';
        outputArea.style.backgroundColor = '#ffe6e6';
    } finally {
        setTimeout(() => {
            outputArea.textContent = '';
            outputArea.style.backgroundColor = 'transparent';
        }, 6000); // Clear message after 6 seconds
    }
}

// --- Media Events for Video ---

const videoPlayer = document.getElementById('promoVideo');
const videoStatusText = document.getElementById('video-load-status');

// Message for when the video is ready to play
function showVideoReady() {
    videoStatusText.textContent = "Video's ready to watch!";
    videoStatusText.style.color = '#28a745';
}

// --- Saving User Preferences (LocalStorage) ---

// Save what kind of events the user likes
function saveMyPreferences() {
    const preferredType = document.getElementById('prefEventType').value;
    localStorage.setItem('myPreferredEventType', preferredType); // Stores it in the browser
    const prefFeedback = document.getElementById('preference-feedback');
    prefFeedback.textContent = `Saved your favorite: "${preferredType}"!`;
    prefFeedback.style.color = '#28a745';
    prefFeedback.style.backgroundColor = '#e6ffed';
    setTimeout(() => {
        prefFeedback.textContent = '';
        prefFeedback.style.backgroundColor = 'transparent';
    }, 3000);
}

// Load preferences when the page opens
function loadMyPreferences() {
    const savedPreference = localStorage.getItem('myPreferredEventType');
    if (savedPreference) {
        document.getElementById('prefEventType').value = savedPreference;
        const prefFeedback = document.getElementById('preference-feedback');
        prefFeedback.textContent = `Welcome back! Your favorite events are set to "${savedPreference}".`;
        prefFeedback.style.color = '#007bff';
        prefFeedback.style.backgroundColor = '#e0f2ff';
        setTimeout(() => {
            prefFeedback.textContent = '';
            prefFeedback.style.backgroundColor = 'transparent';
        }, 3000);
    }
}

// Clear all saved preferences
function clearMyPreferences() {
    localStorage.clear(); // Clears everything from local storage for this site
    sessionStorage.clear(); // Also clears session storage (which is for current session only)
    document.getElementById('prefEventType').value = 'music'; // Reset the dropdown
    const prefFeedback = document.getElementById('preference-feedback');
    prefFeedback.textContent = 'All preferences cleared. Starting fresh!';
    prefFeedback.style.color = '#ff8c66';
    prefFeedback.style.backgroundColor = '#fff0e6';
    setTimeout(() => {
        prefFeedback.textContent = '';
        prefFeedback.style.backgroundColor = 'transparent';
    }, 3000);
}

// --- Finding Your Location (Geolocation) ---

function findMyLocation() {
    const locDisplay = document.getElementById('location-display');
    locDisplay.textContent = 'Trying to find you...';
    locDisplay.style.color = '#555';
    locDisplay.style.backgroundColor = '#f0f0f0';

    if (navigator.geolocation) {
        // Ask the browser for the user's current position
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                locDisplay.textContent = `Found you! Latitude: ${lat.toFixed(4)}, Longitude: ${lon.toFixed(4)}`;
                locDisplay.style.color = '#28a745';
                locDisplay.style.backgroundColor = '#e6ffed';
                console.log("User's location:", position);
            },
            (error) => {
                // Handle different types of errors
                let errMsg = "Couldn't get your location.";
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                        errMsg = "Permission denied. Please allow location access in your browser settings.";
                        break;
                    case error.POSITION_UNAVAILABLE:
                        errMsg = "Location info is currently unavailable.";
                        break;
                    case error.TIMEOUT:
                        errMsg = "Took too long to get location. Try again?";
                        break;
                    case error.UNKNOWN_ERROR:
                        errMsg = "An unexpected error happened.";
                        break;
                }
                locDisplay.textContent = errMsg;
                locDisplay.style.color = '#e63946';
                locDisplay.style.backgroundColor = '#ffe6e6';
                console.error("Geolocation error:", error);
            },
            {
                enableHighAccuracy: true, // Try to get a very precise location
                timeout: 7000, // Wait up to 7 seconds
                maximumAge: 0 // Don't use a cached position
            }
        );
    } else {
        locDisplay.textContent = "Your browser doesn't support finding location.";
        locDisplay.style.color = '#e63946';
        locDisplay.style.backgroundColor = '#ffe6e6';
    }
}

// --- Other Event Handling & Modern JS Features ---

// Double-click an image to make it bigger/smaller
const allEventThumbnails = document.querySelectorAll('.event-thumbnail');
allEventThumbnails.forEach(img => {
    img.ondblclick = function() {
        if (this.style.transform === 'scale(1.3)') {
            this.style.transform = 'scale(1)';
            this.style.zIndex = 'auto';
            this.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.1)';
        } else {
            this.style.transform = 'scale(1.3)'; // Make it 30% larger
            this.style.transition = 'transform 0.3s ease, box-shadow 0.3s ease';
            this.style.zIndex = '100'; // Bring it to the front
            this.style.boxShadow = '0 8px 20px rgba(0, 0, 0, 0.3)';
        }
    };
});

// Warn user if they try to leave the form with unfinished work (onbeforeunload)
window.onbeforeunload = function(event) {
    const formInputs = registrationForm.elements;
    let anyFieldFilled = false;
    for (let i = 0; i < formInputs.length; i++) {
        // Check if any input field (excluding submit button) has content
        if (formInputs[i].type !== 'submit' && formInputs[i].value.trim() !== '') {
            anyFieldFilled = true;
            break;
        }
    }

    if (anyFieldFilled) {
        const warningMessage = "Looks like you're in the middle of filling out the form. Are you sure you want to leave?";
        event.returnValue = warningMessage; // Standard for most browsers
        return warningMessage; // For some older browsers
    }
};

