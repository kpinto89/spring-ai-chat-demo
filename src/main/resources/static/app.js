const chatForm = document.getElementById("chatForm");
const chatLog = document.getElementById("chatLog");
const promptInput = document.getElementById("promptInput");
const sendButton = document.getElementById("sendButton");
const statusText = document.getElementById("statusText");

function appendMessage(role, text) {
    const item = document.createElement("article");
    item.className = `message ${role}`;

    const paragraph = document.createElement("p");
    paragraph.textContent = text;
    item.appendChild(paragraph);

    chatLog.appendChild(item);
    chatLog.scrollTop = chatLog.scrollHeight;
}

async function requestReply(prompt) {
    const response = await fetch(`/chat?prompt=${encodeURIComponent(prompt)}`, {
        method: "GET",
        headers: {
            "Accept": "text/plain"
        }
    });

    if (!response.ok) {
        throw new Error(`Request failed (${response.status})`);
    }

    return response.text();
}

chatForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const prompt = promptInput.value.trim();
    if (!prompt) {
        return;
    }

    appendMessage("user", prompt);
    promptInput.value = "";
    promptInput.focus();

    sendButton.disabled = true;
    statusText.textContent = "Thinking...";

    try {
        const reply = await requestReply(prompt);
        appendMessage("assistant", reply || "(No content returned)");
        statusText.textContent = "";
    } catch (error) {
        appendMessage("assistant", "Sorry, I could not get a response. Please try again.");
        statusText.textContent = error.message;
    } finally {
        sendButton.disabled = false;
    }
});

