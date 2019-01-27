#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define BUFSIZE 200
int main (int argc, char ** argv) {
	/*
	Legge un file di testo e lo visualizza.
	Esempio di utilizzo:
	./testof pippo
	*/
	if (argc<2) {
		printf("Bad parameter count\n");
		return 1;
	}
	char buf[BUFSIZE];
	int lun;
	int i;
	int j;
	FILE *f;
	for (j=1; j<argc; j++) {
		f = fopen(argv[j], "r");
		if (f==NULL) {
			printf("Missing file %s\n", argv[j]);
			continue;
		}
		fgets(buf, BUFSIZE, f);
		while (!feof(f)) {
			lun=strlen(buf);
			for (i=0; i<lun; i++) {
				buf[i] = toupper(buf[i]);
			}
			printf("%s", buf);
			fgets(buf, BUFSIZE, f);
		}
		fclose(f);
	}

	return 0;
}
