package com.example.fantomeapp;

import java.util.Random;

public class SecurityTips {
    private static final String[] securityTips = {
            "Ne pas donner les permissions quand on ne connaît pas",
            "Mettez à jour vos équipements, autant qu'ils le peuvent",
            "Évitez de connecter des câbles inconnus sur votre téléphone",
            "Ne donner uniquement que les permissions requises et cohérentes pour faire fonctionner l'application",
            "Ne faites d'opérations délicates sur un WiFi public",
            "Évitez le plus possible de vous connecter à un WiFi public"
    };

    public static String getRandomTip() {
        Random random = new Random();
        int randomIndex = random.nextInt(securityTips.length);
        return securityTips[randomIndex];
    }
}