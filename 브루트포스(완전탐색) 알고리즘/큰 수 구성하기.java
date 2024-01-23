--- 첫번째 풀이
1부터 주어진 N까지의 탐색하면서 탐색하는 수가 주어진 숫자들로만 이루어져있는지 확인한다
--- 두번째 풀이
하지만 1부터 모든 경우를 확인하는 것이 아닌 주어진 숫자들로 만들 수 있는 모든 경우를 먼저 확인한뒤 
N보다 작은 값이 되었을때를 확인하는 것이 효율적이다.
  -> 시도해보기


package baekjoon18511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
		StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
		int N = toIntValue(tokenizer1);
		int K = toIntValue(tokenizer1);
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			arr.add(toIntValue(tokenizer2));
		}
		for (int i = N; i >= 1; i--) {
			if (isCorrectAnswer(i, arr)) {
				System.out.println(i);
				return;
			}
		}
	}

	private static boolean isCorrectAnswer(int i, List<Integer> arr) {
		while (i > 0) {
			if (!arr.contains(i % 10)) {
				return false;
			}
			i /= 10;
		}
		return true;
	}

	private static int toIntValue(StringTokenizer tokenizer) {
		return Integer.parseInt(tokenizer.nextToken());
	}

}
---------------재귀를 활용한 풀이------------------------
	package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K, arr[], result = 1;

	public static void main(String[] args) throws IOException, NumberFormatException {
		inputValue();
		findResult(0);
		System.out.println(result);
	}

	static void findResult(int sum) {
		for (int i = 0; i < K; i++) {
			/*
			 * 최대값이 유지되는 result를 기준에 넣어준다면 시간을 줄일 수 있다.
			 */
			if (N >= sum + arr[i]) {
				result = Math.max(result, sum + arr[i]);
				findResult((sum + arr[i]) * 10);
			}
		}
	}

	static void inputValue() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
		StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer1.nextToken());
		K = Integer.parseInt(tokenizer1.nextToken());
		arr = new int[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(tokenizer2.nextToken());
		}
	}
}

