#include <stdio.h>

typedef struct {
	char cognome[20];
	char nome [20];
	char sesso;
	int codice;
	float saldo_del_conto;
}  tcliente;


int main(int argc, char ** argv) {
	tcliente cliente;
	tcliente clienti[100];
	tcliente * pcliente;

	pcliente = &cliente;
	cliente.sesso = 'M';
	pcliente->sesso = 'F';
	
	clienti[i].saldo_del_conto = 150.23;

}