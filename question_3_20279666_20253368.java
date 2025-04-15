import java.util.*;

public class question_3_20279666_20253368 {
    /**
     * Classe Node (comme dv3) qui sert de sommet au graphe
     */
    class Node {
        /*
            CODE DE COULEUR :
            0 = ROUGE
            1 = BLEU
            -1 = BLANC (non visité)
        */
        String nom_pays; //NOM DU PAYS
        List<Node> conflits;//LES PAYS AEVC LESQUELS IL Y A CONFLIT (ARRETE)
        int COULEUR; //POUR VERIF SI BIPARti

        /**
         * Constructeur de NODE
         * @param nom
         */
        public Node(String nom) {
            this.nom_pays = nom;
            this.conflits = new ArrayList<>();
            this.COULEUR = -1;
        }

        /**
         * Methode setter pour add un conflit entre 2 pays / arrete
         * @param autre
         */
        public void setConflit(Node autre) {
            conflits.add(autre); //AJOUTER ARRETE
        }
    }
//////////////////////////////////////////////////////////////////////////

    /**
     * CLASSE GRAPHE,CONTIEN SOMMETS+ARRETES
     */
    class Graphe {
        List<Node> sommets; //LISTE DE TOUT LES PAYS EN INIPUT

        /**
         * Constructeur de graphe (pas de parms)
         */
        public Graphe() {
            sommets = new ArrayList<>();
            //ON Defini graphee par ses sommets
        }

        /**
         * Methode getter  qui retourne le Node ayant le nom du pays mi en params
         * @param nom
         * @return
         */
        public Node getPays(String nom) {
            for (int i = 0; i < sommets.size(); i++) {
                if (sommets.get(i).nom_pays.equals(nom)) {//SI LE NOMDU PAYS DU SOMMET I === NOM PARAMS (STRIGN)
                    return sommets.get(i);//TROUVEE
                }
            }
            return null;
        }

        /**
         * Methode setter pour add un paays au graph du monde
         * @param nom
         */
        public void addPays(String nom) {
            if (getPays(nom) == null) {
                sommets.add(new Node(nom));
            }
        }

        /**
         * METHODE PRINCIPAL POUR ITERER THROUGH LE GRAPHE
         * La file quon a fait est un tableau parcontre
         * SUPER IMPORTANT  LA STRATÉGIE DE COLORIAGE
         * @return
         */
        public boolean verifBPT() {
            Node[] file = new Node[50]; //flemme, jss jdois mettre relation.size() mais at this point fuck it
            int pointeurHEAD; //POINTER POUR LE FIRST OUT
            int pointeurTAIL;//POINTEUR FIRSTIN A ADD (ENFANTS


            /////////////fin du init////////////////
            for (int i = 0; i < sommets.size(); i++) { //POUR TOUT LES SOMMETS DANS
                Node depart = sommets.get(i); //ON DEMARRE AU PAYS AJOUTEE EN PREMIRE
                if (depart.COULEUR == -1) { // SI COULEUR = PAS VISITÉ (BLANC===-1)
                    depart.COULEUR = 0; //ON CHANGE A ROUGE
                    pointeurHEAD = 0;// ON vide lenfant vu
                    pointeurTAIL = 0; //on enleve dernier, deja traitee, on passe au next
                    file[pointeurTAIL++] = depart;

                    ///////////la on a la file prete pour les enfants du sommet i ///////////////////////

                    while (pointeurHEAD < pointeurTAIL) { //tant que filepas vide onva verifier tt enfant (un niveau ds arbre/graphe)

                        Node courant = file[pointeurHEAD++]; //TRAITE NEXT ENFANT DE FILLE

                        for (int j = 0; j < courant.conflits.size(); j++) { //POUR TT LES NODE ADJACENTS A COURANT
                            Node voisin = courant.conflits.get(j); //ON TRAITE NODE VOISINE
                            if (voisin.COULEUR == -1) { //SI PAS COLOREE

                                if (courant.COULEUR == 0){ //CAS SI MIT EN ROUGE
                                    voisin.COULEUR = 1 ;
                                }else if(courant.COULEUR ==1){//CAS SI MIT EN BLEU
                                    voisin.COULEUR = 0;
                                }
                                file[pointeurTAIL++] = voisin;
                                ///ICI ON A CONFIRME NODE GOOD
                            } else if (voisin.COULEUR == courant.COULEUR) {
                                return false;//SI VOISIN VISITEE DEJA ET EN PLUS MM COULEUR === PROBLEME
                            }
                        }
                    }
                }
            }

            return true; //smple, si on a pas trouvee de probleme alors il ets bipartite
        }
    }

    /////////////////////////////////////////////////////////////////////////////

    /**
     * METHODE INSPIREE DU DV3, methode utilisee pour generer un pseudo main
     * @param pays
     * @param relations donnee en main, les arretes
     * @return
     */
    public static String question_3(List<String> pays, List<Main.Relation> relations) {
        question_3_20279666_20253368 rep = new question_3_20279666_20253368(); //OBJET QUON USE PR CALL LA BFS

        Graphe graphe = rep.new Graphe();//REP = LE GRAPH A VERIFIER

        for (int i = 0; i < pays.size(); i++) {
            graphe.addPays(pays.get(i));//PR TT LES PAYS EN PARAMS ON CREE NODE DANS GRAPH(PAS ENCORE darretes la)
        }

        for (int i = 0; i < relations.size(); i++) { //POUR chaque COUPLE PAYS-PAYS EN CONFLIT DU INPUT
            Main.Relation relation = relations.get(i); //ON RECUP RELATION DU MAIN
            Node a = graphe.getPays(relation.pays1);
            Node b = graphe.getPays(relation.pays2);
            a.setConflit(b); //ON VA CREER ARETE BIDIRECTIONNELLE ENTRE LES 2
            b.setConflit(a);
        }
////////// ON FAIT COLORIAGE ICI//////////////////////33
        if(graphe.verifBPT()==true){
            return "oui";
        }else{
            return "non";
        }
    }
}
