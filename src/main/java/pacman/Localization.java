package pacman;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;

// $VF: renamed from: a
public final class Localization {
   // $VF: renamed from: a int
   private static int field_108;
   // $VF: renamed from: c java.lang.String
   private String field_109;
   // $VF: renamed from: d java.lang.String
   private String field_110;
   // $VF: renamed from: a java.lang.String
   public String field_111 = "STR_NEW_VERSION_1";
   // $VF: renamed from: b java.lang.String
   public String field_112 = "STR_NEW_VERSION_2";
   // $VF: renamed from: a java.lang.String[]
   public String[] field_113 = new String[6];
   // $VF: renamed from: b java.lang.String[][]
   private String[][] field_114 = new String[20][];
   // $VF: renamed from: b java.lang.String[]
   public static String[] field_115;
   // $VF: renamed from: a java.lang.String[][]
   public static String[][] field_116;

   // $VF: renamed from: a () java.lang.String
   public final String method_51() {
      return this.field_109;
   }

   // $VF: renamed from: a (java.lang.String) boolean
   private boolean method_52(String var1) {
      return this.field_109.equals(var1.toLowerCase());
   }

   // $VF: renamed from: a (java.lang.String) java.lang.String[]
   private static String[] method_53(String var0) {
      if (var0 == null) {
         return null;
      } else {
         int var1 = -1;
         int var2 = 1;

         while (true) {
            var1++;
            if ((var1 = var0.indexOf(10, var1)) == -1) {
               String[] var3 = new String[var2];
               var1 = 0;

               for (int var5 = 0; var5 < var2 - 1; var5++) {
                  int var4 = var1;
                  var1 = var0.indexOf(10, var1);
                  var3[var5] = var0.substring(var4, var1);
                  var1++;
               }

               var3[var2 - 1] = var0.substring(var1);
               return var3;
            }

            var2++;
         }
      }
   }

   // $VF: renamed from: a (java.lang.String[], int) java.lang.String[]
   public static String[] method_54(String[] var0, int var1) {
      if (var0 == null) {
         return new String[]{"null"};
      } else {
         Vector var2 = new Vector(0, 1);
         var1 -= 16;

         for (int var3 = 0; var3 < var0.length; var3++) {
            boolean var4 = false;
            String var5 = (var0[var3].indexOf("%C%") != 0 ? var0[var3] : var0[var3].substring("%C%".length())).trim();

            String var6;
            for (var6 = ""; var5.indexOf("%I%") == 0; var6 = var6 + " ") {
               var5 = var5.substring("%I%".length());
            }

            var5 = var6 + var5;

            while (!var4) {
               int var7 = -1;
               boolean var8 = false;
               if (field_108 == 0) {
                  if (BitmapFont.field_85.method_34(var5, (byte)1) > var1
                     && (var5.indexOf(" ", 0) != -1 || var5.indexOf("/", 0) != -1 || var5.indexOf("-", 0) != -1 || var5.indexOf("\u00ad", 0) != -1)) {
                     boolean var16 = false;
                     int var17 = 0;

                     while (!var16) {
                        int var11 = var17 - 1;
                        if ((var17 = var5.indexOf(" ", var17)) == -1) {
                           var16 = true;
                           var7 = var11 != -1 ? var11 : var17;
                        } else if (BitmapFont.field_85.method_34(var5.substring(0, var17), (byte)1) > var1) {
                           var16 = true;
                           if ((var7 = var11 != -1 ? var11 : var17) == 0) {
                              if (var5.indexOf(45) != -1) {
                                 var7 = var5.indexOf(45) + 1;
                              }

                              if (var5.indexOf(173) != -1) {
                                 var7 = var5.indexOf(173) + 1;
                              }

                              if (var5.indexOf(47) != -1) {
                                 var7 = var5.indexOf(47) + 1;
                              }

                              var8 = true;
                           }
                        } else {
                           var17++;
                        }
                     }
                  }
               } else if (BitmapFont.field_85.method_34(var5, (byte)1) > var1) {
                  boolean var9 = false;
                  int var10 = 0;

                  while (!var9) {
                     var10++;
                     if (var10 > var5.length() || BitmapFont.field_85.method_34(var5.substring(0, var10), (byte)1) > var1) {
                        var9 = true;
                        var7 = var10 > var5.length() ? -1 : var10 - 1;
                     }
                  }
               }

               if (var7 == -1) {
                  var4 = true;
                  var2.addElement((var0[var3].indexOf("%C%") != 0 ? "" : "%C%") + var5);
               } else {
                  var2.addElement((var0[var3].indexOf("%C%") != 0 ? "" : "%C%") + var5.substring(0, var7));
                  if (field_108 == 0) {
                     if (var8) {
                        var5 = var6 + var5.substring(var7);
                     } else {
                        var5 = var6 + var5.substring(var7 + 1);
                     }
                  } else {
                     var5 = var6 + var5.substring(var7);
                  }
               }
            }
         }

         var0 = new String[var2.size()];

         for (int var14 = 0; var14 < var2.size(); var14++) {
            var0[var14] = (String)var2.elementAt(var14);
         }

         System.gc();
         return var0;
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Localization(String var1, boolean var2, boolean var3) {
      try {
         if (var1 != null) {
            int var4;
            if ((var4 = var1.indexOf(45)) != -1) {
               this.field_109 = var1.substring(0, var4).toLowerCase();
               if ((var4 = (var1 = var1.substring(var4 + 1)).indexOf(45)) == -1) {
                  this.field_110 = var1.toLowerCase();
               } else {
                  this.field_110 = var1.substring(var4 + 1).toLowerCase();
               }
            } else {
               this.field_109 = var1;
            }
         }

         if (!this.method_52("ja") && !this.method_52("zh")) {
            field_108 = 0;
         } else {
            field_108 = 2;
         }

         InputStream var29 = null;
         DataInputStream var21 = null;
         if (this.field_110 != null) {
            var29 = this.getClass().getResourceAsStream("/local_" + this.field_109 + "_" + this.field_110 + ".dat");
         }

         if (var29 == null && this.field_109 != null) {
            var29 = this.getClass().getResourceAsStream("/local_" + this.field_109 + ".dat");
         }

         if (var29 == null) {
            var29 = this.getClass().getResourceAsStream("/local.dat");
         }

         if (var29 == null) {
            var29 = this.getClass().getResourceAsStream("local_en.dat");
         }

         boolean var13 = false /* VF: Semaphore variable */;

         label258: {
            label257: {
               try {
                  var13 = true;
                  field_115 = new String[(var21 = new DataInputStream(var29)).readShort()];
                  field_116 = new String[var21.readShort()][];

                  for (int var5 = 0; var5 < field_115.length; var5++) {
                     field_115[var5] = var21.readUTF();
                  }

                  // The menu entry formerly "SEND HIGH SCORE" now just opens the local table.
                  field_115[60] = field_115[82];

                  for (int var32 = 0; var32 < field_116.length; var32++) {
                     field_116[var32] = method_53(var21.readUTF());
                  }

                  var13 = false;
                  break label257;
               } catch (Exception var17) {
                  System.err.println("### Localize:" + var17.toString());
                  var17.printStackTrace();
                  var13 = false;
               } finally {
                  if (var13) {
                     try {
                        if (var21 != null) {
                           var21.close();
                        }

                        if (var29 != null) {
                           var29.close();
                        }
                     } catch (Exception var14) {
                     }
                  }
               }

               try {
                  if (var21 != null) {
                     var21.close();
                  }

                  if (var29 != null) {
                     var29.close();
                  }
               } catch (Exception var15) {
               }
               break label258;
            }

            try {
               var21.close();
               if (var29 != null) {
                  var29.close();
               }
            } catch (Exception var16) {
            }
         }

         this.field_111 = "NewVersion1";
         this.field_112 = "NewVersion2";
         this.field_113[0] = field_115[41];
         this.field_113[1] = field_115[42];
         this.field_113[2] = field_115[43];
         this.field_113[3] = field_115[44];
         this.field_113[4] = field_115[45];
         this.field_113[5] = field_115[46];
         MenuUi.field_26[1] = MenuUi.field_26[2] = new String[]{field_115[86]};
         MenuUi.field_26[18] = new String[]{field_115[126]};
         MenuUi.field_26[3] = new String[]{field_115[97]};
         MenuUi.field_26[4] = method_53(field_115[93]);
         if (var3) {
            MenuUi.field_26[22] = method_53(field_115[73]);
            MenuUi.field_26[23] = method_53(field_115[121]);
         }

         MenuUi.field_26[5] = new String[]{field_115[19]};
         MenuUi.field_26[6] = field_116[2];
         MenuUi.field_26[7] = field_116[3];
         MenuUi.field_26[8] = method_53(field_115[25]);
         MenuUi.field_26[9] = method_53(field_115[26]);
         MenuUi.field_26[10] = new String[]{field_115[28]};
         MenuUi.field_26[11] = method_53(field_115[33]);
         MenuUi.field_26[12] = method_53(field_115[16]);
         MenuUi.field_26[13] = method_53(field_115[94]);
         MenuUi.field_26[14] = method_53(field_115[95]);
         MenuUi.field_26[15] = method_53(field_115[101]);
         MenuUi.field_26[17] = method_53(field_115[119]);
         MenuUi.field_26[16] = method_53(field_115[96]);
         MenuUi.field_27[18] = new String[]{field_115[91]};
         if (var2 && var3) {
            MenuUi.field_27[1] = new String[]{field_115[118], field_115[101], field_115[117], field_115[60], field_115[119]};
         } else if (var3 && !var2) {
            MenuUi.field_27[1] = new String[]{field_115[118], field_115[101], field_115[60], field_115[119]};
         } else if (!var3 && var2) {
            MenuUi.field_27[1] = new String[]{field_115[118], field_115[101], field_115[117], field_115[119]};
         } else {
            MenuUi.field_27[1] = new String[]{field_115[118], field_115[101], field_115[119]};
         }

         MenuUi.field_27[2] = new Object[]{field_115[98], field_115[101], field_115[119], field_115[91]};
         MenuUi.field_27[3] = new String[]{field_115[98], field_115[38]};
         String[] var33 = new String[]{field_115[56], field_115[66]};
         MenuUi.field_27[4] = var33;
         MenuUi.field_27[11] = var33;
         MenuUi.field_27[13] = var33;
         MenuUi.field_27[14] = var33;
         MenuUi.field_27[16] = var33;
         MenuUi.field_27[5] = new String[]{field_115[20], field_115[21]};
         MenuUi.field_27[6] = new Object[0];
         MenuUi.field_27[7] = new String[]{field_115[23], field_115[24]};
         MenuUi.field_27[8] = new String[]{field_115[27]};
         MenuUi.field_27[9] = new String[]{field_115[27]};
         MenuUi.field_27[6] = new Object[0];
         MenuUi.field_27[15] = new String[]{field_115[104], field_115[59], field_115[103], field_115[102]};
         MenuUi.field_27[17] = new String[]{field_115[87], field_115[88], field_115[89], field_115[90], field_115[85]};
         if (var3) {
            MenuUi.field_27[22] = var33;
            MenuUi.field_27[23] = new String[]{field_115[29]};
         }

         this.field_114[0] = method_53(field_115[0]);
         this.field_114[1] = method_53(field_115[1]);
         this.field_114[2] = field_116[0];
         this.field_114[3] = method_53(field_115[2]);
         int var22;
         String var26;
         if ((var22 = (var26 = field_115[3]).indexOf("<MIDLET-NAME>")) != -1) {
            var26 = var26.substring(0, var22) + Pacman.field_0.field_179.getAppProperty("MIDlet-Name") + var26.substring(var22 + "<MIDLET-NAME>".length());
         }

         int var23;
         if ((var23 = var26.indexOf("<MIDLET-VENDOR>")) != -1) {
            var26 = var26.substring(0, var23) + Pacman.field_0.field_179.getAppProperty("MIDlet-Vendor") + var26.substring(var23 + "<MIDLET-VENDOR>".length());
         }

         int var24;
         if ((var24 = var26.indexOf("<MIDLET-VERSION>")) != -1) {
            var26 = var26.substring(0, var24)
               + Pacman.field_0.field_179.getAppProperty("MIDlet-Version")
               + var26.substring(var24 + "<MIDLET-VERSION>".length());
         }

         field_115[3] = var26;
         this.field_114[4] = method_53(field_115[3]);
         this.field_114[5] = method_53(field_115[4]);
         this.field_114[6] = method_53(field_115[5]);
         this.field_114[7] = method_53(field_115[6]);
         this.field_114[8] = method_53(field_115[7]);
         this.field_114[9] = method_53(field_115[8]);
         this.field_114[10] = method_53(field_115[9]);
         this.field_114[11] = field_116[1];
         this.field_114[12] = method_53(field_115[10]);
         this.field_114[13] = method_53(field_115[11]);
         this.field_114[14] = method_53(field_115[4]);
         if (var3) {
            this.field_114[15] = method_53(field_115[124]);
            this.field_114[16] = method_53(field_115[123]);
            this.field_114[17] = method_53(field_115[125]);
            this.field_114[18] = method_53(field_115[122]);
            this.field_114[19] = method_53(field_115[127]);
         }

         MessageScreens.field_64 = new boolean[20];
         int[] var25 = new int[]{5, 14, 6, 10};
         int[] var27 = new int[]{
            0,
            0 + this.field_114[5].length + 1 + 8,
            0,
            0 + this.field_114[14].length + 2 + 8,
            3,
            3 + this.field_114[6].length + 2,
            3,
            3 + this.field_114[10].length
         };

         for (int var34 = var25.length - 1; var34 >= 0; var34--) {
            String[] var30 = new String[var27[(var34 << 1) + 1]];

            for (int var6 = var30.length - 1; var6 >= 0; var6--) {
               var30[var6] = "";
            }

            System.arraycopy(this.field_114[var25[var34]], 0, var30, var27[var34 << 1], this.field_114[var25[var34]].length);
            this.field_114[var25[var34]] = var30;
         }

         String[] var31 = new String[]{""};
         this.field_114[9] = var31;
         this.field_114[8] = var31;
         MessageScreens.field_63 = this.field_114;
         this.field_114 = null;
         (MessageScreens.field_62 = new String[20])[0] = field_115[87];
         MessageScreens.field_62[1] = field_115[88];
         MessageScreens.field_62[2] = field_115[89];
         MessageScreens.field_62[3] = field_115[90];
         MessageScreens.field_62[4] = field_115[85];
         MessageScreens.field_62[11] = field_115[17];
         MessageScreens.field_62[13] = field_115[74];
         if (var3) {
            MessageScreens.field_62[15] = field_115[82];
            MessageScreens.field_62[16] = field_115[83];
            MessageScreens.field_62[17] = field_115[83];
            MessageScreens.field_62[18] = field_115[83];
            MessageScreens.field_62[19] = field_115[82];
         }
      } catch (Exception var19) {
         var19.printStackTrace();
      }
   }
}
