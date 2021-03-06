
#include "stdafx.h"
#include<iostream>
#include<vector>

using namespace std;

void SieveOfEratosthenes(int n)
{	 
	//Initialize a vector of boolean with true
	vector<bool> prime(n+1,true);

	//Start with 2 and for each value that has the value from vector as true marks as
	//false all its multimples less than n
	for (int p = 2; p*p <= n; p++){
		if (prime[p] == true){
			for (int i = p * 2; i <= n; i += p)
				prime[i] = false;
		}
	}
	int c = 0;
	//Print all the prime numbers less than n
	for (int p = 2; p <= n; p++)
		if (prime[p]) {
			c++;
			cout << p << " ";
		}
	cout << c;
}


int main()
{
	int n;
	cout << "Give the number: ";
	cin >> n;
	if (!cin.good()) {
		cout << "Invalid number\n";
		cin >> n;
	}
	else {
		cout << "Following are the prime numbers smaller " << " than or equal to " << n << endl;
		SieveOfEratosthenes(n);
		cin >> n;
	}
	return 0;
}

