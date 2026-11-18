final class OfflineDemo {
    private OfflineDemo() {}

    static void run() {
        show("Winning move", new String[][] {
            {"X", "X", ""},
            {"O", "O", ""},
            {"", "", ""}
        });
        show("Blocking move", new String[][] {
            {"O", "O", ""},
            {"X", "", ""},
            {"", "X", ""}
        });
    }

    private static void show(String label, String[][] board) {
        System.out.println("X to move");
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print((cell.isEmpty() ? "." : cell) + " ");
            }
            System.out.println();
        }
        int[] move = new Logic("X", "O").chooseMove(board, 3);
        System.out.printf("%s: row %d, column %d%n%n", label, move[0] + 1, move[1] + 1);
    }
}
