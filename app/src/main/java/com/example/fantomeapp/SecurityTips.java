package com.example.fantomeapp;

import java.util.Random;

public class SecurityTips {
    private static final String[] securityTips = {
            "N'installez pas des applications en dehors de F-Droid ou autre catalogue sécurisé d'application",
            "Mettez à jour vos équipements, autant qu'ils le peuvent",
            "Évitez de connecter des câbles inconnus sur votre téléphone",
            "Ne donnez uniquement que les permissions requises et cohérentes pour faire fonctionner une application",
            "Ne faites pas d'opérations délicates sur un WiFi public",
            "Évitez le plus possible de vous connecter à un WiFi public",
            "Éteignez ou redémarrez votre téléphone au moins une fois par mois",
            "Utilisez la double authentification quand vous le pouvez",
            "Utilisez des mots de passe robustes",
            "Privilégiez la 4G et 5G, mais n'enlevez pas la 2G ! La 4G et 5G sont plus sécurisées, mais la 2G peut être utile pour les appels d'urgence.",
            "N'activez pas le Bluetooth si vous ne l'utilisez pas"
    };

    private  static  final String[] securityInfoTitle = {
            "Developper mode",
            "ADB Mode",
            "Chiffrement du téléphone",
            "Android Verified Boot",
            "Isolation",
            "Strong Box",
            "Hardware/Software Key",
            "Last Security Patch"
    };

    private  static  final String[] securityInfoDesc = {
            "Le mode développeur du téléphone permet aux développeurs d'utiliser des fonctionnalités inaccessibles aux utilisateurs du quotidien, il est recommandé de désactiver ce mode si vous ne l'utilisez pas",
            "ADB ou Android Debug Bridge permet de faire le lien entre votre téléphone et un terminal externe pour effectuer des communications ou exécuter des lignes de commande sur le téléphone",
            "Si le chiffrement du téléphone est activé, il est pratiquement impossible pour un voleur de récupérer les informations présentes sur le téléphone",
            "Si AVB est présent alors la chaine de démarrage de votre téléphone est sécurisée ",
            "Si l'isolation est activée il est impossible pour une application de communiquer sans droits avec des éléments externes à l'application.",
            "StrongBox est une fonctionnalité de sécurité d'Android utilisant un matériel dédié pour renforcer la protection des clés cryptographiques.",
            "Si le résultat est : Dans une sécurité hardware, cela signifie que les clés sont sauvegardées dans un environnement physique, plus sécurisé qu'un environnement logiciel.",
            "La date indiquée est la date de la dernière installation de patch de sécurité Android"
    };

    public static String getRandomTip() {
        Random random = new Random();
        int randomIndex = random.nextInt(securityTips.length);
        return securityTips[randomIndex];
    }

    public static String getTitle(int i) {
        return securityInfoTitle[i];
    }public static String getDesc(int i) {
        return securityInfoDesc[i];
    }
}