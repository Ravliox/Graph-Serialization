-----------------------------------TEMA-2_ISTRATE-EDUARD_322CB-----------------------------


Capitolul 1. Noduri si adiacente

	Pentru a creea nodurile, folosim o clasa abstracta, cu un constructor ce seteaza toate valorile necesare prelucrarii lor.

			type - tipul nodului 'A', 'B' sau 'C'
			iteration - versiunea nodului 1, 2, sau 3
			name - numele nodului
			adiacenta - de tip Storage pastreaza adiacentele
	Cele 3 tipuri de noduri NodA, NodB si NodC implementeaza clasa abstracta

	Pentru a stoca adiacentele fiecarui nod, implementam o clasa abstracta Storage implementata de SET, LIST si VECTOR. Fiecare clasa are o metoda de adaugare de nod, de eliminare de nod si metode folosite la iterarea prin nodurile adiacente, astfel incat sa se respecte abstractitatea clasei Storage.

	Pentru a selecta metoda de stocare corect, folosim un design pattern de tip Factory ce returneaza Nodului o noua clasa de tip Storage in functie de versiunea acestuia.

Capitolul 2. Citirea grafului si operatii pe acesta

	Citirea fisierului si stocarea grafului se va face in clasa FileWizard. Aceasta incepe sa citeasca fisierul dat din main ca argument in linie de comanda. Liniile vor fi sparte cu metoda .split(" ") dupa spatiu si va fi verificat primul cuvant. In functie de comanda, functia va apela una din metodele corespunzatoare.

	Atunci cand primul cuvant este Settings,  vectorul settings din interiorul clasei va fi modificat dupa valorile citite in linie.
	
	Atunci cand primul cuvant este Add, se va apela metoda add() si se va verifica daca numarul maxim de elemente din graf nu este depasit pentru a putea fi largit graful. Dupa care, se verifica ce tip de nod trebuie instantiat. Nodul respectiv va fi adaugat in graf. Dupa aceasta graful este parcurs si se formeaza adiacentele. Daca numele nodului este egal cu tokens[i] atunci nodurile vor fi adaugate in adiacentele fiecaruia.
	
	Atunci cand primul cuvant este Del, se va apela metoda del() si se va itera prin graful de noduri pana se ajunge la nodul cu numele pasat in linie. Cand se ajunge la nodul necesar, se va intra in adiacenta lui si se va itera printre noduri, fiecaruia dintre noduri stergandu-i-se nodul care trebuie sters din adiacentele lor. Dupa care se va sterge nodul din graful de noduri.
	
	Atunci cand primul cuvant este AddM, se va apela metoda addM() si cele 2 noduri pasate in linia din fisier se vor adauga reciproc in adiacente, dupa ce vor fi gasite in graf.

	Atunci cand primul cuvant este DelM, se va apela metoda delM() si cele 2 noduri pasate in linia din fisiser se vor sterge reciproc din adiacente, dupa ce vor fi gasite in graf.

	Atunci cand primele cuvinte sunt Serializa sau Deserialize atunci se vor efectua cele 2 operatii.

Capitolul 3. Serializarea

	Metoda serialize va deschide fisierul de serializare si va scrie in el folosind un PrintWriter. Pentru inceput se va gasi nodul in graf, pentru a putea incepe parcurgerea. Parcurgerea este de tip arbore in adancime, si foloseste o functie ajutatoare. O data ce un obiect este serializat atunci el va fi pus intr-un nou vector, indexul acestuia fiind si id-ul.

	Serializarea se face incepand in adiacenta nodului original si va continua prin adiacentele fiecarui nod, oprindu-se de fiecare data cand aceasta trebuie sa scrie reference. Va face aceasta daca va gasi numele nodului in vectorul IdList. 

	Pentru scrierea obiectului se va apela metoda writeObject care scrie in fisier informatiile obiectului dupa formatul dat si calculeaza indentarea necesara. 

	La iesirea din fiecare recurenta se va apela functia closeTag care va inchide tagul pentru adiacenta si pentru obiect.

	Singurul parametru suplimentar folosit este nivelul in recurenta care va fi folosit pentru stabilirirea iesirii din recurente la deserializare. Acesta va fi scris in fisier la opening si closing tag ce marcheaza zona nodurilor adiacente.

Capitolul 4. Deserializarea

	Metoda deserializae, ca si serialize, parcurge recursiv in functie de adiacentele nodului. Aceasta va construi primul nod, folosind informatiile de pe primele 2 linii. Dupa care isi va construi un nou String de referinta, end_mark, care ii va semnala ca a terminat de citit adiacentele fiecarui nod.

	Metoda se va apela de fiecare data cand gaseste "<Object" la linia pasata si va iesi recursiv odata ce gaseste sfarsitul adiacentelor, adaugand noduri la adiacenta si la noul graf in acelasi timp. Daca metoda citeste "<Reference" in loc de obiect, aceasta tot ce trebuie sa faca e sa adauge la adiacenta nodului de deasupra nodul din graf de la indexul id.

	Metoda de asemenea va verifica daca instantierea obiectului dupa setarea din fisier este posibila si va scrie rezultatul corespunzator in fisierul log.
