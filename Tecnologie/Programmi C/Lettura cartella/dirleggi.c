#include <stdio.h>
#include <sys/types.h>
#include <dirent.h>


int main(int argc, char ** argv) {
	DIR *d;
	d = opendir(argv[1]);
	struct dirent * entry;
	entry = readdir(d);
	while(entry!=NULL) {
		printf("%ld %s\n", entry->d_ino, entry->d_name);
		entry = readdir(d);
	}

	closedir(d);
	return 0;
}