package pacman;

import pacman.platform.Fnt;
import pacman.platform.Gfx;

// $VF: renamed from: f
public final class MessageScreens {
   // $VF: renamed from: a java.lang.String[]
   static String[] field_62;
   // $VF: renamed from: a java.lang.String[][]
   static String[][] field_63;
   // $VF: renamed from: a boolean[]
   static boolean[] field_64;
   // $VF: renamed from: a int[]
   static int[] field_65;
   // $VF: renamed from: a int
   static int field_66;
   // $VF: renamed from: a boolean
   static boolean field_67;
   // $VF: renamed from: b int
   static int field_68;
   // $VF: renamed from: c int
   static int field_69 = 1024;
   // $VF: renamed from: d int
   static int field_70 = 16777215;
   // $VF: renamed from: e int
   static int field_71 = 16777215;
   // $VF: renamed from: a javax.microedition.lcdui.Fnt
   private static Fnt field_72;
   // $VF: renamed from: h int
   private static int field_73;
   // $VF: renamed from: f int
   public static int field_74;
   // $VF: renamed from: g int
   public static int field_75;

   // $VF: renamed from: a (int) void
   public static void method_27(int var0) {
      if ((var0 & 2) != 0) {
         field_68 = (var0 = field_68 - field_69) > 0 ? var0 : 0;
      } else {
         if ((var0 & 64) != 0) {
            field_68 = (var0 = field_68 + field_69) < field_65[field_66] ? var0 : field_65[field_66];
         }
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx) void
   public static void method_28(Gfx var0) {
      if (MenuUi.field_49 != 0) {
         var0.setClip(0, 0, 360, 640);
         var0.setColor(0);
         var0.fillRect(0, 0, 360, 640);
      }

      MenuUi.field_48 = 0;
      MenuUi.method_17(var0);
      MenuUi.method_18(var0, new String[]{field_62[field_66]});
      MenuUi.method_19(var0);
      Gfx var1 = var0;
      if (!field_64[field_66]) {
         Fnt var4 = field_72;
         int var3 = field_66;
         if (field_63[var3] != null) {
            field_72 = var4;
            field_63[var3] = Localization.method_54(field_63[var3], 310);
         }

         field_64[field_66] = true;
      }

      int var2 = BitmapFont.field_85.method_33((byte)1);
      MenuUi.field_48 += 20;
      field_73 = 317;
      field_74 = 640 - MenuUi.field_41.getHeight() - MenuUi.field_48 - 16;
      var0.setColor(0);
      var0.setClip(0, 0, 360, 640);
      var0.fillRect(22, MenuUi.field_48 - 2, field_73, field_74 + 2);
      int var13 = field_74;
      int var9 = field_66;
      int var5 = BitmapFont.field_85.method_33((byte)1);
      if (field_65 == null) {
         field_65 = new int[field_63.length];
      }

      int var6 = field_63[var9].length;
      field_65[var9] = var6 * var5 + (var6 - 1) * 6 - var13;
      if (field_65[var9] < 0) {
         field_65[var9] = 0;
      }

      if (field_65[var9] != 0) {
         field_65[var9] = field_65[var9] + 2;
      }

      field_65[var9] = field_65[var9] << 8;
      var9 = MenuUi.field_48;
      var13 = MenuUi.field_48 + field_74;
      var5 = MenuUi.field_48 - (field_68 >> 8);
      var6 = 0;
      var0.setColor(field_70);

      while (var6 < field_63[field_66].length && var5 < var13) {
         if (var5 + var2 >= var9) {
            String var7;
            if ((var7 = field_63[field_66][var6]).indexOf("%C%") != 0) {
               BitmapFont.field_85.method_31(var1, var7, 22, var5, 20);
            } else {
               var7 = var7.substring("%C%".length());
               BitmapFont.field_85.method_31(var1, var7, 180, var5, 17);
            }
         }

         var5 += var2 + 6;
         var6++;
      }

      var1.setColor(0);
      var1.setClip(0, 0, 360, 640);
      var1.fillRect(22, var9 - 20, field_73, 18);
      var1.fillRect(22, var13, field_73, 16);
      Pacman.field_0.method_62(22, var9, field_73 - 6 - 40, field_74, 6, 0);
      var9 = field_65[field_66] >> 8;
      var13 = field_68 >> 8;
      if (var9 > 0) {
         if ((field_75 = 50 * field_74 / var9) < var2 << 1) {
            field_75 = var2 << 1;
         }

         if (field_75 > (field_74 << 1) / 3) {
            field_75 = (field_74 << 1) / 3;
         }

         var2 = 22 + field_73 - 6;
         var9 = MenuUi.field_48 + (field_74 - field_75) * var13 / var9;
         var1.setColor(16777215);
         var1.fillRect(var2, var9, 6, field_75);
         var1.fillRect(var2 + 3, MenuUi.field_48, 1, field_74);
         Pacman.field_0.method_62(var2 - 40, var9 - 40, 86, field_75 + 80, 5, 0);
      }

      MenuUi.method_23(var0);
      if (MenuUi.field_49 != 0) {
         MenuUi.field_49--;
      }

      field_67 = true;
      var0.setClip(0, 0, 360, 640);
   }
}
