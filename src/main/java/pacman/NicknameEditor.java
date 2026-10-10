package pacman;

import pacman.platform.Fnt;
import pacman.platform.Gfx;

// $VF: renamed from: h
public final class NicknameEditor extends MenuUi {
   // $VF: renamed from: a boolean[]
   static boolean[] field_54;
   // $VF: renamed from: i int
   private static int field_55;
   // $VF: renamed from: j int
   private static int field_56 = 7368816;
   // $VF: renamed from: e boolean
   private static boolean field_57;
   // $VF: renamed from: a long
   private static long field_58;
   // $VF: renamed from: b long
   private static long field_59;
   // $VF: renamed from: a char[]
   public static char[] field_60 = new char[7];
   // $VF: renamed from: k int
   private static int field_61;

   // $VF: renamed from: a () void
   public static void method_25() {
      field_58 = 0L;
      field_59 = 0L;
      field_57 = true;
      field_55 = 0;
   }

   // $VF: renamed from: a (int) void
   public static void method_12(int var0) {
      MenuUi.method_12(var0);
      field_54 = new boolean[var0];
   }

   // $VF: renamed from: a (int, int) int
   public static int method_26(int var0, int var1) {
      if ((var0 = var0) == 35) {
         var0 = 58;
      }

      var0 -= 47;
      if (var0 > 0 && var0 < 12) {
         var0 = (var0 & 254) << 28 | var0 & 1;
      } else {
         switch (var1) {
            case 1:
               var0 = 2;
               break;
            case 2:
               var0 = 4;
               break;
            case 3:
            case 4:
            case 7:
            default:
               var0 = 0;
               break;
            case 5:
               var0 = 32;
               break;
            case 6:
               var0 = 64;
               break;
            case 8:
               var0 = 256;
         }
      }

      return var0;
   }

   // $VF: renamed from: a (int) boolean
   public static boolean method_13(int var0) {
      boolean var1 = false;
      if (field_54[field_29]) {
         long var2;
         long var4 = (var2 = System.currentTimeMillis()) - field_59;
         field_59 = var2;
         if (var4 > 0L) {
            field_58 += var4;
         }

         if (field_58 >= 500L) {
            field_57 = !field_57;
            field_58 = 0L;
         }

         char[] var12 = field_60;
         if ((var0 & 2) != 0) {
            if (++field_61 >= " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length()) {
               field_61 = 0;
            }

            var12[field_55] = " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(field_61);
         } else if ((var0 & 64) != 0) {
            if (--field_61 < 0) {
               field_61 = " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length() - 1;
            }

            var12[field_55] = " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(field_61);
         } else if ((var0 & 32) != 0) {
            var0 = field_55;
            if (++var0 >= field_60.length) {
               var0 = 0;
            }

            field_55 = var0;
            field_61 = 0;
            char var8 = field_60[field_55];

            for (int var13 = 0; var13 < " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length(); var13++) {
               if (" ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(var13) == var8) {
                  field_61 = var13;
                  break;
               }
            }
         } else if ((var0 & 4) != 0) {
            var0 = field_55;
            if (--var0 < 0) {
               var0 = field_60.length - 1;
            }

            field_55 = var0;
            field_61 = 0;
            char var11 = field_60[field_55];

            for (int var14 = 0; var14 < " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length(); var14++) {
               if (" ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(var14) == var11) {
                  field_61 = var14;
                  break;
               }
            }
         } else {
            var1 = MenuUi.method_13(var0);
         }
      } else {
         var1 = MenuUi.method_13(var0);
         if (field_54[field_29] && field_30 == 0) {
            method_25();
         }
      }

      return var1;
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, javax.microedition.lcdui.Fnt) void
   public static void method_24(Gfx var0, Fnt var1) {
      MenuUi.method_24(var0, var1);
      int var7 = 640 - MenuUi.field_41.getHeight() - MenuUi.field_42.getHeight() - MenuUi.field_50;
      int var8 = MenuUi.field_42.getHeight() + MenuUi.field_50 + (var7 - BitmapFont.field_85.method_33((byte)1) >> 1);
      if (field_54[field_29]) {
         int var2 = BitmapFont.field_85.method_33((byte)1);
         int var3;
         int var4 = (var3 = BitmapFont.field_85.method_36('W', (byte)1)) + 2;
         int var5 = (360 - var4 * field_60.length) / 2;
         var0.setColor(0);
         var0.setClip(0, 0, 360, 640);
         var0.fillRect(var5, var8 - 1, var3 * 9, var2 + 4);

         for (int var6 = 0; var6 < field_60.length; var6++) {
            var0.setClip(0, 0, 360, 640);
            var0.setColor(field_24);
            var0.fillRect(var5, var8 + var2 + 2, var3, 1);
            if (field_57 && field_55 == var6) {
               var0.setColor(field_56);
               var0.fillRect(var5, var8 - 1, var3, var2 + 2);
               if (field_60[var6] != ' ') {
                  BitmapFont.field_85.method_37(var0, (byte)2, field_60[var6], var5 + (var3 - BitmapFont.field_85.method_35(field_60[var6]) >> 1), var8);
               }
            } else {
               var0.setColor(field_24);
               if (field_60[var6] != ' ') {
                  BitmapFont.field_85.method_37(var0, (byte)1, field_60[var6], var5 + (var3 - BitmapFont.field_85.method_35(field_60[var6]) >> 1), var8);
               }
            }

            if (field_55 == var6) {
               var0.setColor(field_24);
               BitmapFont.field_85.method_42(var0, var5, var8 - var2 - 2, 62);
               BitmapFont.field_85.method_42(var0, var5, var8 + var2 + 2, 63);
               Pacman.field_0.method_62(var5 - 40, var8 - var2 - 2 - 40, var3 + 80, var2 + 2 + 40, 4, -1);
               Pacman.field_0.method_62(var5 - 40, var8 + var2 + 2, var3 + 80, var2 + 2 + 40, 4, -2);
            }

            var5 += var4;
         }

         if (field_55 != 0) {
            var5 = (360 - var4 * field_60.length) / 2 - var3 - var3;
            BitmapFont.field_85.method_42(var0, var5, var8, 12);
            Pacman.field_0.method_62(var5 - 40, var8 - 40, var3 + 40, var2 + 80, 4, -3);
         }

         if (field_55 != field_60.length - 1) {
            var5 = (360 - var4 * field_60.length) / 2 + var4 * field_60.length + var3;
            BitmapFont.field_85.method_42(var0, var5, var8, 13);
            Pacman.field_0.method_62(var5, var8 - 40, var3 + 40, var2 + 80, 4, -4);
         }
      }
   }

   static {
      for (int var0 = 0; var0 < 7; var0++) {
         field_60[var0] = ' ';
      }

      char[][] var10000 = new char[][]{
         {'0'},
         {'1'},
         {'A', 'B', 'C', '2'},
         {'D', 'E', 'F', '3'},
         {'G', 'H', 'I', '4'},
         {'J', 'K', 'L', '5'},
         {'M', 'N', 'O', '6'},
         {'P', 'Q', 'R', 'S', '7'},
         {'T', 'U', 'V', '8'},
         {'W', 'X', 'Y', 'Z', '9'},
         {' '}
      };
      field_61 = 0;
   }
}
