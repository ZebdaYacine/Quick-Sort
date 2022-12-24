package faststor;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.Collection;

public class Test  {

    static void échangerÉléments(int[] t, int m, int n)  {
        int temp = t[m];
        t[m] = t[n];
        t[n] = temp;
    }


    static int partition(int[] t, int m, int n)  {
        int v = t[m];                 // valeur pivot
        int i = m - 1;
        int j = n + 1;                  // indice final du pivot
        while (true) {
            do {
                j--;
            } while (t[j] > v);
            do {
                i++;
            } while (t[i] < v);
            if (i < j) {
                échangerÉléments(t, i, j);
            } else {
                return j;
            }
        }
    }

    static void triRapide( int[] t, int m, int n)  {
        if (m < n) {
            int p = partition(t, m, n);
            triRapide(t, m, p);
            triRapide(t, p + 1, n);
        }
    }

}
