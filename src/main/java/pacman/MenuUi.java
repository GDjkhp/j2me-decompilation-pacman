package pacman;

import pacman.platform.Command;
import pacman.platform.Fnt;
import pacman.platform.Gfx;
import pacman.platform.Img;

// $VF: renamed from: n
public class MenuUi {
   // $VF: renamed from: i int
   private static int field_23 = 16777215;
   // $VF: renamed from: a int
   static int field_24 = -2237184;
   // $VF: renamed from: j int
   private static int field_25 = 16776960;
   // $VF: renamed from: a java.lang.String[][]
   static String[][] field_26;
   // $VF: renamed from: a java.lang.Object[][]
   static Object[][] field_27;
   // $VF: renamed from: a boolean[][]
   static boolean[][] field_28;
   // $VF: renamed from: b int
   static int field_29;
   // $VF: renamed from: c int
   static int field_30;
   // $VF: renamed from: d int
   static int field_31 = 0;
   // $VF: renamed from: a boolean
   public static boolean field_32 = true;
   // $VF: renamed from: a int[]
   private static int[] field_33;
   // $VF: renamed from: b int[]
   private static int[] field_34;
   // $VF: renamed from: b boolean
   public static boolean field_35 = true;
   // $VF: renamed from: c boolean
   public static boolean field_36;
   // $VF: renamed from: a javax.microedition.lcdui.Img
   public static Img field_37;
   // $VF: renamed from: b javax.microedition.lcdui.Img
   public static Img field_38;
   // $VF: renamed from: c javax.microedition.lcdui.Img
   public static Img field_39;
   // $VF: renamed from: d javax.microedition.lcdui.Img
   public static Img field_40;
   // $VF: renamed from: e javax.microedition.lcdui.Img
   public static Img field_41;
   // $VF: renamed from: f javax.microedition.lcdui.Img
   public static Img field_42;
   // $VF: renamed from: g javax.microedition.lcdui.Img
   public static Img field_43;
   // $VF: renamed from: h javax.microedition.lcdui.Img
   public static Img field_44;
   // $VF: renamed from: i javax.microedition.lcdui.Img
   public static Img field_45;
   // $VF: renamed from: e int
   public static int field_46;
   // $VF: renamed from: d boolean
   public static boolean field_47;
   // $VF: renamed from: f int
   public static int field_48;
   // $VF: renamed from: g int
   public static int field_49;
   // $VF: renamed from: h int
   public static int field_50;
   // $VF: renamed from: k int
   private static int field_51 = 16;
   // $VF: renamed from: l int
   private static int field_52 = 32;
   // $VF: renamed from: m int
   private static int field_53 = 16;

   // $VF: renamed from: a (int) void
   public static void method_12(int var0) {
      field_26 = new String[var0][];
      field_27 = new Object[var0][];
      field_28 = new boolean[var0][];
      field_33 = new int[var0];
      field_34 = new int[var0];
      field_26[0] = new String[1];
   }

   // $VF: renamed from: a (int) boolean
   public static boolean method_13(int var0) {
      boolean var1 = false;
      if (field_27[field_29] != null && field_27[field_29].length > 0) {
         if ((var0 & 256) != 0) {
            Pacman.field_0.method_101();
            var1 = true;
         } else if ((var0 & 2) != 0) {
            do {
               if (field_30 > 0) {
                  field_30--;
               } else {
                  field_30 = field_27[field_29].length - 1;
               }
            } while (field_27[field_29][field_30] == null || field_28[field_29] != null && field_28[field_29][field_30]);
         } else if ((var0 & 64) != 0) {
            do {
               if (field_30 < field_27[field_29].length - 1) {
                  field_30++;
               } else {
                  field_30 = 0;
               }
            } while (field_27[field_29][field_30] == null || field_28[field_29] != null && field_28[field_29][field_30]);
         }
      }

      return var1;
   }

   // $VF: renamed from: b () void
   public static void method_14() {
      if (field_34 != null) {
         for (int var0 = 0; var0 < field_34.length; var0++) {
            field_34[var0] = 0;
         }
      }

      if (field_33 != null) {
         for (int var1 = 0; var1 < field_33.length; var1++) {
            field_33[var1] = 0;
         }
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Fnt, int, int) void
   public static void method_15(Fnt var0, int var1, int var2) {
      for (int var4 = field_26.length - 1; var4 >= 0; var4--) {
         if (field_26[var4] != null) {
            field_26[var4] = Localization.method_54(field_26[var4], 316);
            Pacman.field_0.repaint();
            Pacman.field_0.serviceRepaints();
         }
      }
   }

   // $VF: renamed from: a (int) int
   public static int method_16(int var0) {
      int var1 = field_27[field_29].length;
      String var3 = Localization.field_115[var0];

      for (int var2 = 0; var2 < var1; var2++) {
         if (field_27[field_29][var2].equals(var3)) {
            return var2;
         }
      }

      return -1;
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx) void
   public static void method_17(Gfx var0) {
      if (field_49 != 0) {
         var0.setClip(0, 0, 360, 640);
         var0.drawImage(field_42, 0, 0, 0);
      }

      field_48 = field_48 + field_42.getHeight();
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, java.lang.String[]) void
   public static void method_18(Gfx var0, String[] var1) {
      int var2 = field_38.getHeight() - 8 >> 1;
      int var3 = BitmapFont.field_85.method_33((byte)1);
      int var4;
      if ((var4 = var1 != null ? var1.length : 0) != 0) {
         if (var1.length != 1 || var1[0] != null && var1[0] != "") {
            int var5 = (var4 * (var3 + 6) - 6 + 16) / var2 * var2;
            int var6 = field_48 + var2 + 8;
            field_50 = var5 + (var2 << 1) - (var2 >> 1);
            if (field_49 != 0) {
               var0.setClip(0, field_48, 360, var2);
               var0.drawImage(field_38, 0, field_48, 0);
            }

            field_48 += var2;
            if (field_49 != 0) {
               int var7 = 0;

               for (int var8 = 0; var7 < var5; var7 += 8) {
                  if (var7 > var5 - 8) {
                     var8 = var5 - var7 - 8;
                  }

                  var0.setClip(0, field_48 + var7, 360, var8 + 8);
                  var0.drawImage(field_38, 0, field_48 + var7 - var2, 0);
               }
            }

            field_48 += var5;
            if (field_49 != 0) {
               var0.setClip(0, field_48, 360, var2);
               var0.drawImage(field_38, 0, field_48 - 8 - var2, 0);
            }

            field_48 += var2;
            if (field_49 != 0) {
               var0.setColor(field_23);

               for (int var9 = 0; var9 < var4; var9++) {
                  BitmapFont.field_85.method_31(var0, var1[var9], 180, var6, 17);
                  var6 += var3 + 6;
               }
            }
         }
      }
   }

   // $VF: renamed from: b (javax.microedition.lcdui.Gfx) void
   public static void method_19(Gfx var0) {
      if (field_49 != 0) {
         int var1 = 640 - field_41.getHeight();
         int var2 = field_48;
         int var3 = 360 - field_40.getWidth();
         var0.setClip(0, 0, 360, 640);

         while (var2 < var1) {
            var0.drawImage(field_39, 0, var2, 0);
            var0.drawImage(field_40, var3, var2, 0);
            var2 += 16;
         }
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, int, int, int) void
   private static void method_20(Gfx var0, int var1, int var2, int var3) {
      int var4 = 0;
      int var5 = field_37.getHeight() / 3;
      var0.setClip(22, var1, field_51, var5);
      var0.drawImage(field_37, 22, var1 - var2 * var5, 0);
      var4 = field_51;
      int var6 = 0;

      int var7;
      for (var7 = 317 - field_51 - field_53; var4 < var7 + field_53; var4 += field_52) {
         if (var4 > var7 - field_53) {
            var6 = var7 - field_53 - var4;
         }

         var0.setClip(var4 + 22, var1, field_52 + var6, var5);
         var0.drawImage(field_37, var4 + 22 - field_53, var1 - var2 * var5, 0);
      }

      var4 = 22 + var7 + field_53;
      var0.setClip(var4, var1, field_53, var5);
      var0.drawImage(field_37, var4 - field_52 - field_53, var1 - var2 * var5, 0);
      var2 = var3 << 2;
      int var12 = GameScreen.field_149[var2 + 2];
      int var9 = GameScreen.field_149[var2 + 3];
      var0.setClip(0, 0, 360, 640);
      Pacman.field_0.method_105(var0, var3, 30 + var12 / 2, var1 + (var5 - var9) / 2);
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, java.lang.Object[]) void
   private static void method_21(Gfx var0, Object[] var1) {
      int var2 = field_48 += 2;
      int var3 = field_37.getHeight() / 3;
      int var4 = BitmapFont.field_85.method_33((byte)1);
      int var5 = var1 != null ? var1.length : 0;
      int var6 = 640 - field_41.getHeight() - var2;
      int var7 = var3 + 4;
      int var8 = var6 / var7;
      if (field_47) {
         Pacman.field_0.method_62(22, field_48, 316, var6, 6, 0);
         int var9 = -var7 * (var5 - var8);
         if (!Pacman.field_0.field_254) {
            int var10 = field_46 / var7 * var7;
            if (field_46 % var7 < -var7 / 2) {
               var10 -= var7;
            }

            if (var10 > 0) {
               var10 = 0;
            }

            if (var10 < var9) {
               var10 = var9;
            }

            int var11 = var2 + field_30 * (var3 + 4);

            while (var11 + var10 < var2) {
               var10 += var7;
            }

            while (var11 + var10 + var3 > var2 + var6) {
               var10 -= var7;
            }

            if (field_46 != var10) {
               if ((var6 = (var10 - field_46) / 5) == 0) {
                  var6 = var10 > field_46 ? 1 : -1;
               }

               field_46 += var6;
            }
         }

         if (field_46 > 0) {
            field_46 = 0;
         }

         if (field_46 < var9) {
            field_46 = var9;
         }
      }

      field_48 = field_48 + field_46;
      if (var5 == 0) {
         field_48 = field_48 + (var3 - var4 >> 1);
      } else {
         for (int var21 = 0; var21 < var5; var21++) {
            boolean var15;
            if (!(var15 = field_28[field_29] != null && field_28[field_29][var21]) && var1[var21] != null) {
               if (field_48 + var3 < var2) {
                  field_48 += var3 + 4;
               } else {
                  if (field_48 >= var2 + var8 * var7) {
                     break;
                  }

                  char[] var16;
                  String var18;
                  if (var1[var21] instanceof char[]) {
                     var16 = (char[])var1[var21];
                     var18 = null;
                  } else {
                     var16 = null;
                     var18 = (String)var1[var21];
                  }

                  int var12 = 75 + var21 % 8;
                  if (field_32 && var18 != null) {
                     if (var18 != null && Localization.field_115 != null) {
                        if (var18.equals(Localization.field_115[118])) {
                           var12 = 4;
                        } else if (var18.equals(Localization.field_115[101])) {
                           var12 = 82;
                        } else if (var18.equals(Localization.field_115[104]) || var18.equals(Localization.field_115[105])) {
                           var12 = 81;
                        } else if (var18.equals(Localization.field_115[91])) {
                           var12 = 22;
                        } else if (var18.equals(Localization.field_115[99])) {
                           var12 = 73;
                        } else if (var18.equals(Localization.field_115[100])) {
                           var12 = 71;
                        }
                     }

                     int var13 = 0;
                     if (field_30 == var21) {
                        var13 = 1;
                        if (field_31 == 1) {
                           var13 = 2;
                        }
                     }

                     method_20(var0, field_48, var13, var12);
                  }

                  int var23 = field_48 + (var3 - var4 >> 1);
                  if (var18 != null) {
                     if (field_32) {
                        var0.setColor(field_25);
                        if (Localization.field_115 != null && var18.equals(Localization.field_115[117])) {
                           var0.setColor(16711680);
                        }

                        BitmapFont.field_85.method_31(var0, var18, 61, var23, 20);
                     } else {
                        var0.setColor(16777215);
                        BitmapFont.field_85.method_31(var0, var18, 180, var23, 17);
                     }
                  } else if (GameScreen.field_173[3] != 23 && var16 != null) {
                     int var19 = 0;
                     if (field_30 == var21) {
                        var19 = 1;
                        if (field_31 == 1) {
                           var19 = 2;
                        }
                     }

                     method_20(var0, field_48, var19, var12);
                     var0.setColor(field_25);
                     BitmapFont.field_85.method_29(var0, var16, 0, var16.length, 61, var23, 20);
                  }

                  int var17 = field_48;
                  int var20 = var21 == 0 ? -2 : 0;
                  var12 = var21 != 0 && var21 != var5 - 1 ? 0 : 2;
                  if (!var15) {
                     if (GameScreen.field_173[3] == 38) {
                        if (field_30 == var21) {
                           Pacman.field_0.method_62(22, var17 - 2 + var20, 316, var3 + 2 + var12, 4, -5);
                        } else {
                           Pacman.field_0.method_62(22, var17 - 2 + var20, 316, var3 + 2 + var12, 4, -2);
                        }
                     } else {
                        Pacman.field_0.method_62(22, var17 - 2 + var20, 316, var3 + 2 + var12, 0, var21 + 2);
                     }
                  }

                  if (field_32) {
                     field_48 += var3 + 4;
                  } else {
                     field_48 += var4 + 4;
                  }
               }
            }
         }
      }
   }

   // $VF: renamed from: d (javax.microedition.lcdui.Gfx) void
   private static void method_22(Gfx var0) {
      int var1 = field_27[field_29] != null ? field_27[field_29].length : 0;
      int var2 = 640 - field_41.getHeight() - field_48 - 2;
      int var3 = field_37.getHeight() / 3;
      if (field_47 = var2 < var1 * (var3 + 4) && field_32) {
         var2 = field_43.getWidth();
         var3 = field_43.getHeight();
         int var4 = field_38.getHeight() - 8 >> 1;
         int var5 = 330 - var2;
         var4 = field_48 - (var4 >> 1) - field_50 + (field_50 - var3 >> 1);
         var0.setClip(var5, var4, var2, var3);
         var0.setColor(field_30 == 0 ? 8947712 : 16776960);
         var0.fillRect(var5, var4, var2, var3 >> 1);
         var0.setColor(field_30 == var1 - 1 ? 8947712 : 16776960);
         var0.fillRect(var5, var4 + (var3 >> 1) + 1, var2, var3 >> 1);
         var0.drawImage(field_43, var5, var4, 0);
         Pacman.field_0.method_62(var5 - 40, var4 - 40, var2 + 80, (var3 >> 1) + 40, 4, -1);
         Pacman.field_0.method_62(var5 - 40, var4 + (var3 >> 1) + 1, var2 + 80, (var3 >> 1) + 40, 4, -2);
      }
   }

   // $VF: renamed from: c (javax.microedition.lcdui.Gfx) void
   public static void method_23(Gfx var0) {
      int var1 = 640 - field_41.getHeight();
      if (field_49 != 0) {
         var0.setClip(0, 0, 360, 640);
         var0.drawImage(field_41, 0, var1, 0);
         var0.setColor(16777215);
         if (((Command)Pacman.field_0.field_278.firstElement()).getLabel() != " ") {
            String var2 = ((Command)Pacman.field_0.field_278.firstElement()).getLabel();
            BitmapFont.field_85.method_31(var0, var2, 102, 598, 17);
         }

         if (((Command)Pacman.field_0.field_278.lastElement()).getLabel() != " ") {
            String var5 = ((Command)Pacman.field_0.field_278.lastElement()).getLabel();
            BitmapFont.field_85.method_31(var0, var5, 260, 598, 17);
         }
      }

      var1 = field_41.getHeight();
      if (GameScreen.field_173[3] == 38) {
         Pacman.field_0.method_62(0, var1, 180, var1, 4, -5);
      } else if (((Command)Pacman.field_0.field_278.firstElement()).getLabel() != " ") {
         Pacman.field_0.method_62(0, var1, 180, var1, 1, 0);
      }

      if (((Command)Pacman.field_0.field_278.lastElement()).getLabel() != " ") {
         Pacman.field_0.method_62(180, var1, 180, var1, 1, 1);
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, javax.microedition.lcdui.Fnt) void
   public static void method_24(Gfx var0, Fnt var1) {
      try {
         if (field_47 || GameScreen.field_173[3] == 59) {
            field_49 = 1;
         }

         if (field_49 != 0) {
            var0.setClip(0, 0, 360, 640);
            var0.setColor(0);
            var0.fillRect(0, 0, 360, 640);
         }

         field_48 = 0;
         method_17(var0);
         if (field_27[field_29] != null && (field_27[field_29].length != 0 || GameScreen.field_173[3] == 23)) {
            method_18(var0, field_26[field_29]);
         }

         method_22(var0);
         method_19(var0);
         if (field_27[field_29] == null || field_27[field_29].length == 0 && GameScreen.field_173[3] != 23) {
            field_32 = false;
            field_46 = 0;
            field_30 = 0;
            method_21(var0, field_26[field_29]);
            field_32 = true;
         } else {
            method_21(var0, field_27[field_29]);
         }

         method_23(var0);
         if (field_47) {
            field_48 = field_42.getHeight();
            if (field_27[field_29] != null && (field_27[field_29].length != 0 || GameScreen.field_173[3] == 23)) {
               method_18(var0, field_26[field_29]);
            }

            method_22(var0);
            var0.setClip(0, 0, 360, 640);
            var0.setColor(0);
            var0.fillRect(22, field_48, 316, 2);
            int var5 = field_37.getHeight() / 3;
            int var2;
            int var3 = (var2 = 640 - field_41.getHeight() - field_48) / (var5 + 4);
            int var6 = var2 - var3 * (var5 + 4);
            var2 = 640 - field_41.getHeight() - var6;
            var0.setClip(0, 0, 360, 640);
            var0.setColor(0);
            var0.fillRect(22, var2, 316, var6);
         }

         if (field_49 != 0) {
            field_49--;
         }
      } catch (Exception var4) {
      }
   }
}
