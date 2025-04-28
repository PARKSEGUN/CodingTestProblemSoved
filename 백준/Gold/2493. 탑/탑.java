import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Stack<Tower> towers = new Stack<>();
	static List<Integer> answer = new ArrayList<>();

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(reader.readLine());
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(tokenizer.nextToken());
			while (towers.size() > 0) {
				if (towers.peek().getValue() < tmp) {
					towers.pop();
				} else {
					break;
				}
			}
			if (towers.size() == 0) {
				answer.add(0);
			} else {
				answer.add(towers.peek().getIdx() + 1);
			}
			towers.add(new Tower(tmp, i));
		}
		for (int i = 0; i < N; i++) {
			resultString.append(answer.get(i)).append(" ");
		}
		System.out.println(resultString);
	}

}

class Tower {
	private int value;
	private int idx;

	public Tower(int value, int idx) {
		super();
		this.value = value;
		this.idx = idx;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Override
	public String toString() {
		return value + "";
	}

}