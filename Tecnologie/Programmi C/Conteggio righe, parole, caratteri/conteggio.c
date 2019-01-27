#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define BUFSIZE 200
int main (int argc, char ** argv) {
	if (argc<2) {
		printf("Bad parameter count\n");
		return 1;
	}
	char buf[BUFSIZE];
	int nRighe;
	int nParole;
	int nCarat;
	int i;
	int j;
	int k;
	int totR=0;
	int totP=0;
	int totC=0;
	int parola;
	FILE *f;
	for (j=1; j<argc; j++) {
		f = fopen(argv[j], "r");
		if (f==NULL) {
			printf("Missing file %s\n", argv[j]);
			continue;
		}
		nRighe=0;
		nParole=0;
		nCarat=0;
		fgets(buf, BUFSIZE, f);
		while (!feof(f)) {
			nRighe++;
			nCarat+=strlen(buf);
			i=0;
			while (buf[i]==' ') {
				i++;
			}
			parola=0;
			for (i=i+1; i<strlen(buf); i++) {
				if (buf[i]==' ') {
					k=i;
					while (buf[k]==' ') {
						k++;
					}
					i=k;
					if (buf[i]!='\n' && buf[i]!='\0') {
						nParole++;
					}
				} else {
					parola=1;
				}
			}
			if (parola==1) {
				nParole++;
			}
			fgets(buf, BUFSIZE, f);
		}
		printf("%6d %6d %6d %s\n", nRighe, nParole,  nCarat, argv[j]);
		if (argc>2) {
			totR+=nRighe;
			totP+=nParole;
			totC+=nCarat;
		}
		fclose(f);
	}
	if (argc>2) {
		printf("%6d %6d %6d Totale\n", totR, totP, totC);
	}
	return 0;
}
