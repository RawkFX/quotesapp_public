import axios from "axios";

export async function getQuote() {
    try {
        return await axios.get("https://quotesapp-pgie.onrender.com/api/v1/quote");
    } catch (error) {
        console.error("Error fetching quote:", error);
        return null;
    }
}
