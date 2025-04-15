public class question_2_20279666_20253368 {
    public static int question2(int[][] arr) {
        // avant tout  trouver le nombre de sommets dans le graphe
        int maxNode = 0;   // intialiser maxNode a 0

        // On cherche le plus grand numero de sommet
        for (int i = 0; i < arr.length; i++) {   // d'apres main :  arretes[i] = {u,v, poids }
            int u = arr[i][0];   // sommet de depart de l'arete numero i
            int v = arr[i][1];   // sommet d'arrivee de du meme arete
            if (u > maxNode) {
                maxNode = u;
            }
            if (v > maxNode) {
                maxNode = v;
            }
        }

        boolean[] present = new boolean[maxNode + 1]; // tableau pour verifier les sommet qui existe
        for (int i = 0; i < arr.length; i++) {
            int u = arr[i][0];
            int v = arr[i][1];
            present[u] = true;
            present[v] = true;
        }

        int V = 0; // nombre total de sommets
        for (int i = 0; i < present.length; i++) {
            if (present[i]) {
                V++; // on compte juste les sommets  presents
            }
        }

        // inB indique si un sommet fait partie de l'arbre construit (ensemble B dans le pseudo-code)
        boolean[] inB = new boolean[maxNode + 1]; // ce tableau pour savoir quel sommet sont deja dans le nuage B
        int cmptInB = 0; // combien de sommets sont dans B ( le nuage )

        //  on ajoute un sommet arbitraire a B (ici le premier sommet rencontré)
        int firstNode = arr[0][0];  // je chosi ce sommet arbitraire pour commence
        inB[firstNode] = true;     // ca devient true puisque il va etre dans le nuage
        cmptInB++;                 // pi on incremente pour keep track de combien de sommet on a

        int totalCost = 0; // Ce sera la somme des
        // poids des arêtes sélectionnées

        // Tant qu'on n'a pas inclus tous les sommets dans B
        while (cmptInB < V) {
            int minWeight = 10000000; //VU QUON CHERCHE UNE ARETE + PETITE ON VEUCX S ASSURER QUE TT LES CHOIX SONT + PETIT QUAU DEPART
            int idxEdge = -1;

            // On parcourt toutes les aretes pour trouver celle avec le plus petit poids
            // reliant un sommet dans l'ensemble  B a un ensemble qui n'est pas inclue dans B
            for (int i = 0; i < arr.length; i++) {
                int u = arr[i][0];
                int v = arr[i][1];
                int weight = arr[i][2];

                // Vrifie si une extremite est dans B et l'autre pas
                if ((inB[u] && !inB[v]) || (inB[v] && !inB[u])) { // u dans B et pas dans v ou v dans B et pas u
                    if (weight < minWeight) { // si le poid de cette arete est plus pti
                        minWeight = weight;   // on met ajour le plus ptit
                        idxEdge = i; // pui on garde l'index enregistrer dans cette variable pour l'utiliser
                        // apres
                    }
                }
            }

            // cas de graph non-connex
            if (idxEdge == -1) {
                return -1;      // champs non reliable
            }

            // On recupere l'aret apartir de la variable qu'on a enregistre
            // rappel edge[i]={u,v,poid}
            int u = arr[idxEdge][0];
            int v = arr[idxEdge][1];
            int poid = arr[idxEdge][2];

            // On ajoute son poids au cout total
            totalCost += poid;

            // On ajoute les nouveaux sommets a l'ensemble b
            if (!inB[u]) {
                inB[u] = true;
                cmptInB++;    // on incremente le compteur de B
            }
            if (!inB[v]) {
                inB[v] = true;
                cmptInB++;
            }
        }

        // Une fois on passe sur tout les sommet on return le cout Total
        return totalCost;
    }
}
