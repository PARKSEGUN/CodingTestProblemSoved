#include<iostream>
#include<algorithm>

using namespace std;

int arr[10001];

int main(void) {

	int N;
	int count_min = 0, count_plu = 0, count_zeo = 0, sum = 0;
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		if (arr[i] < 0) {
			count_min++;
		}
		else if (arr[i] > 0) {
			count_plu++;
		}
		else if (arr[i] == 0) {
			count_zeo++;
		}
	}

	sort(arr, arr + N);


	for (int i = 0; i < N; i++) {
		if (arr[i] < 0) {
			if (count_min % 2 == 0) {
				sum += (arr[i] * arr[i + 1]);
				i++;
			}
			else {
				if (count_min == 1) {
					if (arr[i + 1] == 0 && i + 1 < N) { i++; }
					else {
						sum += arr[i];
					}
				}
				else {
					sum += (arr[i] * arr[i + 1]);
					count_min -= 2;
					i++;
				}

			}
		}
		else if (arr[i] > 0) {
			if (count_plu % 2 == 0) {
				if (arr[i] == 1) {
					sum += arr[i] + arr[i + 1];
					i++;
				}
				else {
					sum += (arr[i] * arr[i + 1]);
					i++;
				}
			}
			else {
				sum += arr[i];
				count_plu--;
			}
		}
	}

	cout << sum << endl;

}