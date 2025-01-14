조합을 이용한 완전탐색(재귀) 구현




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Food foods[];
	static int answer = Integer.MAX_VALUE;

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;
	static StringBuilder resultString = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(reader.readLine());
		foods = new Food[N];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int sour = Integer.parseInt(tokenizer.nextToken());
			int bitter = Integer.parseInt(tokenizer.nextToken());
			foods[i] = new Food(sour, bitter);
		}
		selectFood(0, 1, 0);
		System.out.println(answer);
	}

	private static void selectFood(int start, int sour, int bitter) {
		if (start != 0) {
			answer = Math.min(answer, Math.abs(sour - bitter));
		}
		for (int i = start; i < foods.length; i++) {
			selectFood(i + 1, sour * foods[i].getSour(), bitter + foods[i].getBitter());
		}
	}

}

class Food {
	private int sour;
	private int bitter;

	public Food(int sour, int bitter) {
		super();
		this.sour = sour;
		this.bitter = bitter;
	}

	public int getSour() {
		return sour;
	}

	public void setSour(int sour) {
		this.sour = sour;
	}

	public int getBitter() {
		return bitter;
	}

	public void setBitter(int bitter) {
		this.bitter = bitter;
	}

	@Override
	public String toString() {
		return "Food [sour=" + sour + ", bitter=" + bitter + "]";
	}

}
