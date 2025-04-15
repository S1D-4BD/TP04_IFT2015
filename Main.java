//Matricule de l'équipe: 1234567 et 7654321
import java.util.*;

public class Main {
    static class Relation {
        String pays1;
        String pays2;

        public Relation(String pays1, String pays2) {
            this.pays1 = pays1;
            this.pays2 = pays2;
        }
    }

    public static void main(String[] args) {
        List<List<Object>> params = List.of(
                // Question 1 params
                List.of(
                        List.of("d5", "a1"),
                        List.of("d2", "a7", "b4", "c2", "e6")
                ),
                // Question 2 params
                List.of(
                        new int[][] {
                                {1, 2, 3},
                                {2, 3, 4},
                                {1, 3, 5}
                        },
                        new int[][] {
                                {1, 4, 3},
                                {2, 3, 4},
                                {3, 2, 5}
                        }
                ),
                // Question 3 params
                List.of(
                        List.of("USA", "USSR", "Cuba", "UK"),
                        List.of(
                                new Relation("USA", "USSR"),
                                new Relation("USA", "Cuba"),
                                new Relation("USSR", "UK")
                        )
                ),
                List.of(
                        List.of("USA", "USSR", "Cuba", "UK", "Inde", "Pakistan", "Chine"),
                        List.of(
                                new Relation("USA", "USSR"),
                                new Relation("USA", "Cuba"),
                                new Relation("USSR", "UK"),
                                new Relation("Inde", "Pakistan"),
                                new Relation("Chine", "USA"),
                                new Relation("Chine", "Inde"),
                                new Relation("USSR", "Pakistan")
                        )
                )
        );

        List<List<Object>> expectedAnswers = List.of(
                // Question 1 results
                List.of("4 b7 f7 b3 f3 h1\n6 h8", "4 b8 d8 f8 h8 a7 c7 e7 g7 h6 b4 f4\n5 h7 h5 h3 g2 f1 h1\n4 f8 h8 g7 d6 h6 g5 h4 g3 d2 h2 g1\n5 h8\n4 c8 g8 c4 g4 a2 b1 d1 f1 h1"),
                // Question 2 results
                List.of(7, -1),
                // Question 3 results
                List.of("oui", "non")
        );

        List<String> question1Results = new ArrayList<>();
        question1Results.add(question1((List<String>) params.get(0).get(0)));
        question1Results.add(question1((List<String>) params.get(0).get(1)));
        for (int i = 0; i < question1Results.size(); i++) {
            String result = question1Results.get(i);
            String expected = (String) expectedAnswers.get(0).get(i);
            if (result.equals(expected)) {
                System.out.println("Question 1 Test " + (i + 1) + ": ✅ Correct (" + result + ")");
            } else {
                System.out.println("Question 1 Test " + (i + 1) + ": ❌ Incorrect (" + result + "), attendu: " + expected);
            }
        }

        List<Integer> question2Results = new ArrayList<>();
        question2Results.add(question2((int[][]) params.get(1).get(0)));
        question2Results.add(question2((int[][]) params.get(1).get(1)));
        for (int i = 0; i < question2Results.size(); i++) {
            int result = question2Results.get(i);
            int expected = (int) expectedAnswers.get(1).get(i);
            if (result == expected) {
                System.out.println("Question 2 Test " + (i + 1) + ": ✅ Correct (" + result + ")");
            } else {
                System.out.println("Question 2 Test " + (i + 1) + ": ❌ Incorrect (" + result + "), attendu: " + expected);
            }
        }

        List<String> question3Results = new ArrayList<>();
        question3Results.add(question3((List<String>) params.get(2).get(0), (List<Relation>) params.get(2).get(1)));
        question3Results.add(question3((List<String>) params.get(3).get(0), (List<Relation>) params.get(3).get(1)));
        for (int i = 0; i < question3Results.size(); i++) {
            String result = question3Results.get(i);
            String expected = (String) expectedAnswers.get(2).get(i);
            if (result.equals(expected)) {
                System.out.println("Question 3 Test " + (i + 1) + ": ✅ Correct (" + result + ")");
            } else {
                System.out.println("Question 3 Test " + (i + 1) + ": ❌ Incorrect (" + result + "), attendu: " + expected);
            }
        }
    }

    public static String question1(List<String> list) {
        question_1_20279666_20253368.affichagegrillewow(list); //bonuussss
        return question_1_20279666_20253368.question_1(list);
    }

    public static int question2(int[][] arr) {
        return question_2_20279666_20253368.question2(arr); //TODO
    }

    public static String question3(List<String> pays, List<Relation> relations) {
        return question_3_20279666_20253368.question_3(pays,relations);

    }
}
