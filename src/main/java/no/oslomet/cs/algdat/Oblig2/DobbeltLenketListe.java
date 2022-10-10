package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

import static java.util.Objects.requireNonNull;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    private Node<T> finnNode(int indeks){

        indeksKontroll(indeks,false);

        if(indeks < (antall / 2)){
            Node<T> brukes = hode;
            for(int  i = 0; i < indeks; i++){
                brukes = brukes.neste;
            }
            return brukes;
        }


        else {
            Node<T> brukes = hale;
            for(int i = antall - 1; i > indeks; i--){
                brukes = brukes.forrige;
            }
            return brukes;
        }


    }


    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        requireNonNull(a);

        if(a.length > 0) {
            int iPos = 0;
            for (; iPos < a.length; iPos++) {
                if (a[iPos] != null) {
                    hode = new Node<>(a[iPos]);
                    antall++;
                    break;
                }
            }
            hale = hode;
            if(hode != null){
                iPos++;
                for(;iPos < a.length; iPos++){
                    if(a[iPos] != null){
                        hale.neste = new Node<>(a[iPos], hale, null);
                        hale = hale.neste;
                        antall++;
                    }
                }
            }
        }
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);
        int tell = til - fra;
        if(tell < 1) return new DobbeltLenketListe<>();

        Node<T> brukes = finnNode(fra);

        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

        while(tell > 0) {
            subliste.leggInn(brukes.verdi);
            brukes = brukes.neste;
            tell--;
        }
        return subliste;
    }

    public static void fratilKontroll(int lengde, int fra, int til)
    {
        if (fra < 0)
            throw new IndexOutOfBoundsException
                    (fra + ") er negativt.");

        if (til > lengde)
            throw new IndexOutOfBoundsException
                    (til + ") er storre enn legnden.");

        if (fra > til)
            throw new IllegalArgumentException
                    ("fra er storre en til");
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall==0;
    }

    @Override
    public boolean leggInn(T verdi) {

        requireNonNull(verdi);

        if (hode == null) {
            hode = new Node<>(verdi);
            hale=hode;
            antall+=1;
            endringer++;

        }
        else {
            hale.neste=new Node<>(verdi,hale,null);
            hale=hale.neste;
            antall++;
            endringer++;
        }

        return true;
    }


    @Override
    public void leggInn(int indeks, T verdi) {

        requireNonNull(verdi);
        indeksKontroll(indeks,true);

        if (indeks==0 && antall==0){leggInn(verdi);}
        else if (indeks==0){
            hode.forrige=new Node<>(verdi,null,hode);
            hode = hode.forrige;
            antall++;
            endringer++;
        }
        else if (indeks==antall){
            hale.neste=new Node<>(verdi,null,hode);
            hale=hale.neste;
            antall++;
            endringer++;
        }


        }
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) >= 0;
    }

    @Override
    public T hent(int indeks) {
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> brukes = hode;
        int indeks = 0;
        while (brukes != null){
            if(brukes.verdi.equals(verdi)) return indeks;
            indeks++;
            brukes = brukes.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        requireNonNull(nyverdi);

        Node<T> node = finnNode(indeks);
        T t = node.verdi;
        node.verdi = nyverdi;
        endringer++;
        return t;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        Node<T> brukes=hode;
        StringBuilder bygger = new StringBuilder();
        bygger.append('[');
        if (brukes!=null){
            bygger.append(brukes.verdi);
            brukes = brukes.neste;
        }
        while (brukes!=null){
            bygger.append(',');
            bygger.append(' ');
            bygger.append(brukes.verdi);
            brukes=brukes.neste;
        }
        bygger.append(']');
        return bygger.toString();
    }

    public String omvendtString() {
        Node<T> brukes=hale;
        StringBuilder bygger = new StringBuilder();
        bygger.append('[');
        if (brukes!=null){
            bygger.append(brukes.verdi);
            brukes = brukes.forrige;
        }
        while (brukes!=null){
            bygger.append(',');
            bygger.append(' ');
            bygger.append(brukes.verdi);
            brukes=brukes.forrige;
        }
        bygger.append(']');
        return bygger.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


