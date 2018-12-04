package dongphanalkyl;

import java.io.File;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LietKe {

    private static int soDP[] = {1, 1, 1, 2, 4, 8, 17, 39, 89, 211, 507, 1238, 3057};
    private static String ct[][] = new String[13][];
    private static String ctNguoc[][] = new String[13][];

    public static void main(String[] args) {
        ct[0] = new String[1];
        ct[0][0] = "H";
        ct[1] = new String[1];
        ct[1][0] = "CH3";
        ctNguoc[0] = new String[1];
        ctNguoc[0][0] = "H";
        ctNguoc[1] = new String[1];
        ctNguoc[1][0] = "CH3";
        PrintWriter writer = null;

        for (int soC = 2; soC <= 12; soC++) {
            try {
                ct[soC] = new String[soDP[soC]];
                ctNguoc[soC] = new String[soDP[soC]];
                writer = new PrintWriter(new File("" + soC + ".txt"));
                int chiSo = 0;
                for (chiSo = 0; chiSo < ct[soC - 1].length; chiSo++) {
                    ct[soC][chiSo] = ct[soC - 1][chiSo] + "-CH2";
                    ctNguoc[soC][chiSo] = "CH2-" + ct[soC - 1][chiSo];
                    writer.write(ct[soC][chiSo] + "-OH\r\n");
                }
                int du = (soC - 1) / 2;
                for (int veTrai = 1; veTrai <= du; veTrai++) {
                    int vePhai = soC - 1 - veTrai;
                    if (vePhai == veTrai) {
                        for (int cVeTrai = 0; cVeTrai < ct[veTrai].length; cVeTrai++) {
                            for (int cVePhai = cVeTrai; cVePhai < ct[vePhai].length; cVePhai++) {
                                if (cVeTrai == cVePhai) {
                                    ct[soC][chiSo] = "(" + ct[veTrai][cVeTrai] + ")2CH";
                                    ctNguoc[soC][chiSo] = "CH(" + ctNguoc[veTrai][cVeTrai] + ")2";
                                } else {
                                    ct[soC][chiSo] = ct[veTrai][cVeTrai] + "-CH(" + ctNguoc[vePhai][cVePhai] + ")";
                                    ctNguoc[soC][chiSo] = "CH(" + ctNguoc[vePhai][cVePhai] + ")-" + ctNguoc[veTrai][cVeTrai];
                                }
                                writer.write(ct[soC][chiSo] + "-OH\r\n");
                                ++chiSo;
                            }
                        }
                    } else {
                        for (int cVeTrai = 0; cVeTrai < ct[veTrai].length; cVeTrai++) {
                            for (int cVePhai = 0; cVePhai < ct[vePhai].length; cVePhai++) {
                                ct[soC][chiSo] = ct[vePhai][cVePhai] + "-CH(" + ctNguoc[veTrai][cVeTrai] + ")";
                                ctNguoc[soC][chiSo] = "CH(" + ctNguoc[veTrai][cVeTrai] + ")-" + ctNguoc[vePhai][cVePhai];
                                writer.write(ct[soC][chiSo] + "-OH\r\n");
                                ++chiSo;
                            }
                        }
                    }
                }
                du = (soC - 1) / 3;
                for (int veTrai = 1; veTrai <= du; veTrai++) {
                    for (int veGiua = veTrai;; veGiua++) {
                        int vePhai = soC - 1 - veTrai - veGiua;
                        if (veGiua > vePhai) {
                            break;
                        }
                        if (veTrai == veGiua) {
                            if (veGiua == vePhai) {
                                for (int cVeTrai = 0; cVeTrai < ct[veTrai].length; cVeTrai++) {
                                    for (int cVeGiua = cVeTrai; cVeGiua < ct[veGiua].length; cVeGiua++) {
                                        for (int cVePhai = cVeGiua; cVePhai < ct[vePhai].length; cVePhai++) {
                                            if (cVeTrai == cVeGiua) {
                                                if (cVeGiua == cVePhai) {
                                                    ct[soC][chiSo] = "(" + ct[veTrai][cVeTrai] + ")3C";
                                                    ctNguoc[soC][chiSo] = "C(" + ctNguoc[veTrai][cVeTrai] + ")3";
                                                } else {
                                                    ct[soC][chiSo] = ct[vePhai][cVePhai] + "-C(" + ctNguoc[veTrai][cVeTrai] + ")2";
                                                    ctNguoc[soC][chiSo] = "C(" + ctNguoc[veTrai][cVeTrai] + ")2-" + ctNguoc[vePhai][cVePhai];
                                                }
                                            } else {
                                                if (cVeGiua == cVePhai) {
                                                    ct[soC][chiSo] = ct[veTrai][cVeTrai] + "-C(" + ctNguoc[vePhai][cVePhai] + ")2";
                                                    ctNguoc[soC][chiSo] = "C(" + ctNguoc[vePhai][cVePhai] + ")2-" + ctNguoc[veTrai][cVeTrai];
                                                } else {
                                                    ct[soC][chiSo] = ct[vePhai][cVePhai] + "-C(" + ctNguoc[veTrai][cVeTrai] + ")(" + ctNguoc[veGiua][cVeGiua] + ")";
                                                    ctNguoc[soC][chiSo] = "C(" + ctNguoc[veGiua][cVeGiua] + ")(" + ctNguoc[veTrai][cVeTrai] + ")-" + ctNguoc[vePhai][cVePhai];
                                                }
                                            }
                                            writer.write(ct[soC][chiSo] + "-OH\r\n");
                                            ++chiSo;
                                        }
                                    }
                                }
                            } else {
                                for (int cVeTrai = 0; cVeTrai < ct[veTrai].length; cVeTrai++) {
                                    for (int cVeGiua = cVeTrai; cVeGiua < ct[veGiua].length; cVeGiua++) {
                                        for (int cVePhai = 0; cVePhai < ct[vePhai].length; cVePhai++) {
                                            if (cVeTrai == cVeGiua) {
                                                ct[soC][chiSo] = ct[vePhai][cVePhai] + "-C(" + ctNguoc[veTrai][cVeTrai] + ")2";
                                                ctNguoc[soC][chiSo] = "C(" + ctNguoc[veTrai][cVeTrai] + ")2-" + ctNguoc[vePhai][cVePhai];
                                            } else {
                                                ct[soC][chiSo] = ct[vePhai][cVePhai] + "-C(" + ctNguoc[veTrai][cVeTrai] + ")(" + ctNguoc[veGiua][cVeGiua] + ")";
                                                ctNguoc[soC][chiSo] = "C(" + ctNguoc[veGiua][cVeGiua] + ")(" + ctNguoc[veTrai][cVeTrai] + ")-" + ctNguoc[vePhai][cVePhai];
                                            }
                                            writer.write(ct[soC][chiSo] + "-OH\r\n");
                                            ++chiSo;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (veGiua == vePhai) {
                                for (int cVeTrai = 0; cVeTrai < ct[veTrai].length; cVeTrai++) {
                                    for (int cVeGiua = 0; cVeGiua < ct[veGiua].length; cVeGiua++) {
                                        for (int cVePhai = cVeGiua; cVePhai < ct[vePhai].length; cVePhai++) {
                                            if (cVeGiua == cVePhai) {
                                                ct[soC][chiSo] = ct[veTrai][cVeTrai] + "-C(" + ctNguoc[vePhai][cVePhai] + ")2";
                                                ctNguoc[soC][chiSo] = "C(" + ctNguoc[vePhai][cVePhai] + ")2-" + ctNguoc[veTrai][cVeTrai];
                                            } else {
                                                ct[soC][chiSo] = ct[vePhai][cVePhai] + "-C(" + ctNguoc[veTrai][cVeTrai] + ")(" + ctNguoc[veGiua][cVeGiua] + ")";
                                                ctNguoc[soC][chiSo] = "C(" + ctNguoc[veGiua][cVeGiua] + ")(" + ctNguoc[veTrai][cVeTrai] + ")-" + ctNguoc[vePhai][cVePhai];
                                            }
                                            writer.write(ct[soC][chiSo] + "-OH\r\n");
                                            ++chiSo;
                                        }
                                    }
                                }
                            } else {
                                for (int cVeTrai = 0; cVeTrai < ct[veTrai].length; cVeTrai++) {
                                    for (int cVeGiua = 0; cVeGiua < ct[veGiua].length; cVeGiua++) {
                                        for (int cVePhai = 0; cVePhai < ct[vePhai].length; cVePhai++) {
                                            ct[soC][chiSo] = ct[vePhai][cVePhai] + "-C(" + ctNguoc[veTrai][cVeTrai] + ")(" + ctNguoc[veGiua][cVeGiua] + ")";
                                            ctNguoc[soC][chiSo] = "C(" + ctNguoc[veGiua][cVeGiua] + ")(" + ctNguoc[veTrai][cVeTrai] + ")-" + ctNguoc[vePhai][cVePhai];
                                            writer.write(ct[soC][chiSo] + "-OH\r\n");
                                            ++chiSo;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println(soC);
            } catch (Exception ex) {
                Logger.getLogger(LietKe.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                writer.close();
            }
        }
    }

}
