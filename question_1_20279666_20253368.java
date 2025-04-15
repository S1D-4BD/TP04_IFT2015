import java.util.List; //pour le GRAPH, liste de VOISINS
public class question_1_20279666_20253368 {

    static int[][] movesCheval = {
            //ORDRE HORAIRE DES MOVEMENTS POSSIBLES DU CHEVAL (CADRANT HAUT DROITE FIRST ET LE RESTE QUI VIENT)
            {1, 2},{2, 1},{2, -1},{1, -2},{-1, -2},{-2, -1},{-2, 1},{-1, 2}
    };

    /**
     * Methode pour affichage a la fin, on afiche les COORDONNEES
     * en mode lettre-chiffre a partir d'une des 64 cases
     * @param index
     * @return
     */
    public static String posCOORD(int index) {
        int colonne = index % 8; //
        int rangee = index / 8;
        return "" + (char) ('a' + colonne) + (rangee + 1);
    }
    /**
     * Methode pour CALCULS, a partir du string de coordonnee
     * lettre-chiffre on trouve la CASE sur 64
     * @param coordoneee
     * @return la case de lechiquier correspondante
     */
    public static int posCHIFFRE(String coordoneee) {
        int lettre = coordoneee.charAt(0) - 'a'; //ON RECUP LE PREMIER CHAR DU STRING -> SI == a ON A 0 SI == h ON A 7
        int chiffre = coordoneee.charAt(1) - '1'; //ECHIQIURE START A 0 -> RETTRER le CHAR 1 POUR AVOIR BON ECART
        int caseEchiquier = (8 * chiffre) +lettre; //calcul de la case == 8 fois rangee + le decalage
        return caseEchiquier;
    }

    /**
     * METHODE DE BFS -> PARCOURS LARGEUR
     * @param depart
     * @return
     */
    public static int[] BFSCHEVAL(int depart) {
        int[] echiquier_board = new int[64]; //LES ETATS DE VISITE = BIG LISTE DES CASES SI -1 DONC PAS VISITED

        for (int i = 0; i < 64; i++) { // ICI BOARD DE 0 A 63
            echiquier_board[i] = -1;//LA ON VA REMPLIR TT LES CASES DE -1 PAS VISITEE AU DEBUT
        }
        ///ATTENTION //
        //on sais que ca va jamais depasser 64 cases ANYWAYS plus simple pour moi car jamais fait queue en ift1025
        int[] file = new int[64];
        int pointeurFirstOut = 0; //LE NEXT A ETRE TRAITEE (HEAD)
        int pointeurFirstIn = 0; // LA CASE OU ON ADD LE PREMER ENFANT (TAIL)

        echiquier_board[depart] = 0; //ON IDENTIFIE LA CASE DEPART A 0 SAUTS
        file[pointeurFirstIn++] = depart; //ON MET LE PREMIER ELEM (LA OU ON DEMARRE

        ////////////////// on start bfs ici //////////////////
        while (pointeurFirstOut < pointeurFirstIn) { //CONDITION QUE LA FILE EST PAS VIDE
            int courant = file[pointeurFirstOut++]; //ON TRAITE LE NEXT ENFANT
            int colonne = courant % 8;      //GET POSITION DS ECHIQUIER
            int rangee = courant / 8;

            for (int i = 0; i < 8; i++) { //ON RECUP LE MOUVEMENT EN L DU CHEVAL PARMIS 8 ,ENSUITE ON PREND SA PARTIE EN ABSISCE (0) ET EN ORDONNEE (1)
                int moveHorizontal = movesCheval[i][0];
                int moveVertical = movesCheval[i][1];
                int updateH = colonne + moveHorizontal; //CALCUL NOUVELLE POSITION PROBABLE, PAS ENCORE CONFIRMEE LA CA VIENT APRES
                int updateV = rangee + moveVertical; ///////

                ////////////////// on verifie si on add ou pas ici //////////////////
                if (updateH >= 0 && updateH < 8 && updateV >= 0 && updateV < 8) { //SI ON DEPASSE DE ECHIQUIER EN LARGER OU HAUTEUR PAS BON
                    int nextPosition = updateH + 8 * updateV;//ON VALIDE LA POSITION COMME ETANT DANS L ECUIQUIER
                    if (echiquier_board[nextPosition] == -1) { // SI EN PLUS ELLE EST PAS VISITEE
                        echiquier_board[nextPosition] = echiquier_board[courant] + 1; //POUR ALLER A LA NEXT ON PART DE NBR SAUT COURANT +1
                        file[pointeurFirstIn++] = nextPosition; ////UPDATE ----- ON DECALE A DROITE LE PROCHIN IN ////
                    }
                }
                ////
            }
        }

        return echiquier_board;
    }

    /**
     * Methode pour trier les cases
     * @param cases
     * @param taille
     */
    public static void trierCasestrouvees(String[] cases, int taille) {


        for (int i = 0; i < taille - 1; i++) {
            for (int j = i + 1; j < taille; j++) {
                char col1 = cases[i].charAt(0);
                int row1 = cases[i].charAt(1) - '0';

                char col2 = cases[j].charAt(0);
                int row2 = cases[j].charAt(1) - '0';

                if (row1 < row2 || (row1 == row2 && col1 > col2)) {
                    // échanger
                    String temp = cases[i];
                    cases[i] = cases[j];
                    cases[j] = temp;
                }
            }
        }
    }

    /**
     * Methode qui va prendre chaque element de la list des params,
     * call une BFS dessus, et retourner le nombre de sauts le plus grand possible,
     * et lister les autres cases au mm niveau
     * @param init
     * @return
     */
    public static String question_1(List<String> init) {
        String reponse = ""; //

        for (int i = 0; i < init.size(); i++) { //ON ITERE LA LIST
            String position = init.get(i); //// position == un string de coordonnee pas traitee
            int depart = posCHIFFRE(position); //change vers int (BFS QUON A FAIT AVEC INT)
            int[] echiquier = BFSCHEVAL(depart);
            /////////// on a fait la BFS ici ///////////

            //tres bete mais bon, on va juste passer a travers pr get le max (PAS OPTIMAL I KNOW)
            int max = 0;
            for (int j = 0; j < 64; j++) {
                if (echiquier[j] > max) {
                    max = echiquier[j]; //ON UPDATE MAX si on trouve un plus grand
                }
            }

            /////////// on sait c quoi le max de sauts ici ///////////
            String[] casesMaxSauts = new String[64];
            int taille = 0;
            for (int j = 0; j < 64; j++) {
                if (echiquier[j] == max) { //si nbr de suats dans echiquier == max
                    casesMaxSauts[taille++] = posCOORD(j); // on la add dans les casesMaxSauts
                }
            }

            /////////// on sait c quelles cases atteignable en MAX sauts ici ///////////

            trierCasestrouvees(casesMaxSauts, taille); //sort this shit


            String elem = String.valueOf(max); //ecrire en string le nbr de sauts max omfg again
            for (int j = 0; j < taille; j++) {
                elem += " " + casesMaxSauts[j]; //on print apres MAX TT LES ELEMENTS A CETTE MM DISTANCE
            }

            reponse += elem; //ON CONCAT A REPONSE

            if (i + 1 < init.size()) { //wtf jai tellemnt eu de debug a faire juste a cause de ce truc que jai pas add
                reponse += "\n"; //TANT QUON EST PAS AU DERNIER PARAM DE LIST ON ADD LINEBRAK APRES AVOIR MIT TT LES CASES
            }
        }

        return reponse;
    }
    //////////////////////////////////////////////////////////////////////////
    ////////////////////// GROS BONUS A CONSIDERER ///////////////////////////
    //////////////////////////////////////////////////////////////////////////


    //on a fait un affichage 2d et ca fait pas crash le code, just appelee avant

public static void affichagegrillewow(List<String> init) {
    for (int i = 0; i < init.size(); i++) {
        String depart = init.get(i); //JUSTE EN TEST
        int departINT = posCHIFFRE(depart); //changer en index POUR BFS
        int[] chemin = BFSCHEVAL(departINT);   //faire bfs avec depart

        System.out.println("start" + depart);

        for (int y = 7; y >= 0; y--) { //print du HAUT --> BAS
            System.out.print((y + 1) + " "); //ON PRINT LE NUM DE LA RANGEE ACTUELLE
            for (int x = 0; x < 8; x++) { // EET LA ON PRINT LES VALEURS DEC COLS PR LA RANGEE
                int index = x + 8 * y;
                if (chemin[index] == -1) {
                    System.out.print(". ");
                } else {
                    System.out.print(chemin[index] + " "); //reoutr ligne
                }
            }
            System.out.println();
        }


        System.out.print("  ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(c + " ");
        }
        System.out.println();


        //////////////

        int max = 0;
        for (int j = 0; j < 64; j++) {
            if (chemin[j] > max) {
                max = chemin[j];
            }
        }


        System.out.println(" ");

        for (int y = 7; y >= 0; y--) { //
            System.out.print((y + 1) + " ");
            for (int x = 0; x < 8; x++) {
                int index = x + 8 * y;
                if (chemin[index] == max) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }

        //
        System.out.print("  ");
        for (char col = 'a'; col <= 'h'; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
        System.out.println("//----------------------------//");
    }
/////
}

}

