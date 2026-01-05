/* src/api.js */

export async function apiFetch(url, options = {}) {
const token = localStorage.getItem("jwtToken");

const headers = {
"Content-Type": "application/json",
...(options.headers || {}),
};

if (token) {
headers["Authorization"] = `Bearer ${token}`;
}

const response = await fetch(url, {
...options,
headers,
});

if (!response.ok) {
const text = await response.text();
throw new Error(text || `Greška pri komunikaciji sa serverom (${response.status})`);
}

// ako odgovor nije prazan, parsiraj JSON
try {
return await response.json();
} catch {
return null;
}
}
