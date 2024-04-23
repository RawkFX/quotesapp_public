'use client';

import {getQuote as fetchQuoteFromService} from "@/services/quoteService";
import React, {useState, useEffect} from "react";

export default function LandingPage() {
    const [quoteData, setQuoteData] = useState({ quote: "", author: "" });
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchQuote = async () => {
            try {
                const response = await fetchQuoteFromService();
                if (response && response.data) {
                    setQuoteData(response.data);
                } else {
                    setQuoteData({ quote: "Citatele sunt ca fluturii, nu le poți prinde cu mâna, dar le poți prinde cu inima.", author: "Anonim" });
                }
            } catch (error) {
                console.error("Nu am putut prelua datele:", error);
                setQuoteData({ quote: "O eroare a aparut, va rugam contactati administratorul", author: "contact@fadevox.com" });
            } finally {
                setIsLoading(false);
            }
        };

        fetchQuote().then(r => {
            console.log(r); // Printeaza in consola raspunsul
        });
    }, []); // Array gol inseamna ca efectul se executa o singura data, la montarea componentei

    return (
        <div className="relative flex flex-col items-center justify-center h-screen bg-gradient-to-br from-gray-900 to-purple-700 dark:bg-opacity-70">
            {isLoading ?
            (<div className="absolute inset-0 bg-black bg-opacity-50 flex items-center justify-center">
                <div className="text-white text-2xl">Se incarca...</div>
            </div>) :
            (<div className="max-w-md text-center text-white text-xl lg:text-2xl">
                <span>{quoteData.quote}</span>
                <p className="text-white text-sm mt-10">- {quoteData.author}</p>
            </div>)}
        </div>
    );
}