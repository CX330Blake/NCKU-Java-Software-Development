import java.util.*;

public class SudokuValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> testCases = new ArrayList<>();

        while (scanner.hasNextLine()) {
            List<String> grid = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (!scanner.hasNextLine())
                    break;
                String line = scanner.nextLine().trim();
                if (line.isEmpty())
                    break; // New line indicates the end of a test case
                grid.add(line);
            }
            if (!grid.isEmpty()) {
                testCases.add(grid.toArray(new String[0]));
            }
        }
        scanner.close();

        for (int i = 0; i < testCases.size(); i++) {
            boolean isValid = validateSudoku(testCases.get(i));
            System.out.println("Case " + (i + 1) + ": " + (isValid ? "True" : "False") + ".");
        }
    }

    private static boolean validateSudoku(String[] board) {
        // Check if each row, column, and 3x3 subgrid satisfies the Sudoku rule
        for (int i = 0; i < 9; i++) {
            if (!isValidSet(getRow(board, i)) ||
                    !isValidSet(getColumn(board, i)) ||
                    !isValidSet(getSubgrid(board, i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidSet(char[] numbers) {
        Set<Character> set = new HashSet<>();
        for (char num : numbers) {
            if (set.contains(num))
                return false; // Duplicate number found
            set.add(num);
        }
        return set.size() == 9;
    }

    private static char[] getRow(String[] board, int row) {
        return board[row].toCharArray();
    }

    private static char[] getColumn(String[] board, int col) {
        char[] column = new char[9];
        for (int i = 0; i < 9; i++) {
            column[i] = board[i].charAt(col);
        }
        return column;
    }

    private static char[] getSubgrid(String[] board, int index) {
        char[] subgrid = new char[9];
        int rowStart = (index / 3) * 3;
        int colStart = (index % 3) * 3;
        int k = 0;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                subgrid[k++] = board[i].charAt(j);
            }
        }
        return subgrid;
    }
}
