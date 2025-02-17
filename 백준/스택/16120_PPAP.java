처음에 문자열을 잘라주면서 해결했을때는 substr() 함수사용으로 시간초과 발생 찾아보니 O(N)  의 시간복잡도를 갖고있는 함수
  이를 해결하기위해서 PPAP를 찾았을때의 조건을 확인 P가 2번 A 한번 P 1한번 과 같은 규칙을 적용해서 해결



---------------코드-----------------


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static String input;

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		input = reader.readLine();
		Stack<Character> stack = new Stack<>();
		int pCount = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == 'P') {
				pCount++;
				stack.add(input.charAt(i));
			} else {
				if (pCount >= 2 && i + 1 < input.length() && input.charAt(i + 1) == 'P') {
					stack.pop();
					stack.pop();
					pCount -= 2;
				} else {
					System.out.println("NP");
					return;
				}
			}
		}
		System.out.println((stack.size() == 1) ? "PPAP" : "NP");
	}

}
