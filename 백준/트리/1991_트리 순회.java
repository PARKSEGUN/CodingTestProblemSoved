import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int leftNodes[];
	static int rightNodes[];

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;
	static StringBuilder resultString = new StringBuilder();

	public static void main(String[] args) throws IOException {
		setting();
		printPreOrder(0);
		resultString.append("\n");
		printInOrder(0);
		resultString.append("\n");
		printPostOrder(0);
		System.out.println(resultString);
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		leftNodes = new int[N + 1];
		rightNodes = new int[N + 1];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			char root = tokenizer.nextToken().charAt(0);
			char left = tokenizer.nextToken().charAt(0);
			char right = tokenizer.nextToken().charAt(0);
			leftNodes[root - 'A'] = (left != '.') ? left - 'A' : -1;
			rightNodes[root - 'A'] = (right != '.') ? right - 'A' : -1;
		}
	}

	private static void printPostOrder(int cur) {
		if (cur == -1) {
			return;
		}

		printPostOrder(leftNodes[cur]);
		printPostOrder(rightNodes[cur]);
		resultString.append((char) (cur + 'A'));

	}

	private static void printInOrder(int cur) {
		if (cur == -1) {
			return;
		}

		printInOrder(leftNodes[cur]);
		resultString.append((char) (cur + 'A'));
		printInOrder(rightNodes[cur]);

	}

	private static void printPreOrder(int cur) {
		if (cur == -1) {
			return;
		}
		resultString.append((char) (cur + 'A'));
		printPreOrder(leftNodes[cur]);
		printPreOrder(rightNodes[cur]);
	}

}
