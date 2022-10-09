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
        throw new UnsupportedOperationException();
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

        if (hode == hale) {
            hode = new Node<>(verdi,hale,null);
            hale=hode;
            antall+=1;

        }
        else {
            hale.neste=new Node<>(verdi,hale,null);
            hale=hale.neste;
            antall++;
        }
        endringer++;
        return true;
    }


    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
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


