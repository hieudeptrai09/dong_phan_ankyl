package dongphanalkyl;

public class MayTinh {

    public long b1[], b2[], b3[], ester[];

    private long b1(int n) {
        if (n == 0) {
            return 1;
        } else {
            return (b1(n - 1) + b2(n - 1) + b3(n - 1));
        }
    }

    private long b2(int n) {
        int du = n / 2, i;
        long s = 0, save;
        for (i = 1; i <= du; i++) {
            if (i != n - i) {
                s += b1[i] * b1[n - i];
            } else {
                save = b1[i];
                s += save * (save + 1) / 2;
            }
        }
        return s;
    }

    private long b3(int n) {
        int du = n / 3, i, j;
        long s = 0, save;
        for (i = 1; i <= du; i++) {
            for (j = i; j <= n - i - j; j++) {
                if (i == j) {
                    if (j == n - i - j) {
                        save = b1[i];
                        s += save * (save + 1) * (save + 2) / 6;
                    } else {
                        save = b1[i];
                        s += save * (save + 1) / 2 * b1[n - i - j];
                    }
                } else if (j == n - i - j) {
                    save = b1[j];
                    s += save * (save + 1) / 2 * b1[i];
                } else {
                    s += b1[i] * b1[j] * b1[n - i - j];
                }
            }
        }
        return s;
    }

    private long este(int n) {
        long s = 0;
        for (int i = 0; n - i - 1 > 0; i++) {
            s += b1[i] * b1[n - i - 1];
        }
        return s;
    }

    public MayTinh() {
        b1 = new long[50];
        b2 = new long[50];
        b3 = new long[50];
        ester = new long[50];
        for (int i = 0; i < 50; i++) {
            b1[i] = b1(i);
            b2[i] = b2(i);
            b3[i] = b3(i);
            ester[i] = este(i);
        }
    }
}
