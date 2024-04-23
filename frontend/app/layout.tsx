import "./globals.css";
import React from "react";

const metadata = {
    title: "QuoteSphere: Inspiră-te zilnic cu cele mai frumoase citate",
    description: "Descoperă o colecție inspirată de cele mai remarcabile citate, de la gânditori celebri până la cuvinte motivaționale care îți încântă sufletul. În acest colț virtual, vei găsi o sursă de inspirație și reflecție, alcătuită din cuvinte care îți încântă simțurile și îți îmbogățesc experiența de viață.",
    keywords: "Citate celebre, Motivaționale, Frumoase, Gânditori celebri, Inspirție, Reflecție, Cuvinte inspiratoare, Experiență de viață, Îmbogățire personală, Sursă de inspirație.",
    ogImage: "/ogImage.jpeg",
    ogType: "Citate",
    author: "Răzvan P.",
    publish_date: "2024-02-26T00:00:00-0600",
    apple_touch_icon: "/apple-touch-icon.png",
    icon_32: "/favicon-32x32.png",
    icon_16: "/favicon-16x16.png",
    manifest: "/site.webmanifest"
};

interface RootLayoutsProps {
    children: React.ReactNode;
}

export default function RootLayout({children}: RootLayoutsProps) {
    return (
        <html lang="en">
        <head>
            <title>{metadata.title}</title>
            <meta name="description" content={metadata.description} />
            <meta name="keywords" content={metadata.keywords} />

            <meta name="image" property="og:image" content={metadata.ogImage} />
            <meta name="type" property="og:type" content={metadata.ogType}/>
            <meta name="author" content={metadata.author} />
            <meta name="publish_date" property="og:publish_date" content={metadata.publish_date} />

            <link rel="apple-touch-icon" sizes="180x180" href={metadata.apple_touch_icon} />
            <link rel="icon" type="image/png" sizes="32x32" href={metadata.icon_32} />
            <link rel="icon" type="image/png" sizes="16x16" href={metadata.icon_16} />
            <link rel="manifest" href={metadata.manifest} />
        </head>
        <body>
            {children}
        </body>
        </html>
    );
}