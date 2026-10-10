package pacman;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import pacman.platform.Fnt;
import pacman.platform.Gfx;
import pacman.platform.Img;

// $VF: renamed from: e
public final class BitmapFont {
   // $VF: renamed from: a boolean
   private boolean field_76 = false;
   // $VF: renamed from: a int[]
   private int[] field_77;
   // $VF: renamed from: b int[]
   private int[] field_78;
   // $VF: renamed from: a byte[]
   private byte[] field_79;
   // $VF: renamed from: a long[][]
   private long[][] field_80;
   // $VF: renamed from: c int[]
   private int[] field_81;
   // $VF: renamed from: a javax.microedition.lcdui.Fnt
   private Fnt field_82;
   // $VF: renamed from: a javax.microedition.lcdui.Img[]
   private Img[] field_83;
   // $VF: renamed from: a byte
   private byte field_84 = 1;
   // $VF: renamed from: a e
   public static BitmapFont field_85 = new BitmapFont();

   public BitmapFont() {
      "PLTE".getBytes();
      this.field_83 = new Img[10];
      this.field_79 = new byte[1108];

      for (int var1 = 0; var1 < 1108; var1++) {
         this.field_79[var1] = -1;
      }

      this.field_80 = new long[10][];
      this.field_77 = new int[10];
      this.field_78 = new int[10];
      this.field_81 = new int[10];
      this.field_82 = Fnt.getDefaultFont();
      this.field_77[0] = this.field_82.getHeight();
      this.field_78[0] = this.field_82.getBaselinePosition();
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, java.lang.Object, int, int, int, int, int) void
   public final void method_29(Gfx var1, Object var2, int var3, int var4, int var5, int var6, int var7) {
      if (var2 instanceof String) {
         this.method_31(var1, ((String)var2).substring(var3, var4), var5, var6, var7);
      } else {
         this.method_31(var1, new String((char[])var2, var3, var4), var5, var6, var7);
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, byte, java.lang.Object, int, int, int, int, int) void
   public final void method_30(Gfx var1, byte var2, Object var3, int var4, int var5, int var6, int var7, int var8) {
      this.method_32(
         var1, (byte)4, new String((char[])var3, var4, 7), var6, var7, 20, var1.getClipX(), var1.getClipY(), var1.getClipWidth(), var1.getClipHeight()
      );
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, java.lang.String, int, int, int) void
   public final void method_31(Gfx var1, String var2, int var3, int var4, int var5) {
      int var7 = var1.getColor();
      BitmapFont var6 = this;
      int var8 = 1;

      int var10000;
      while (true) {
         if (var8 >= var6.field_81.length) {
            var10000 = -1;
            break;
         }

         if (var6.field_81[var8] == var7) {
            var10000 = var8;
            break;
         }

         var8++;
      }

      int var9 = var10000;
      if (var10000 == -1) {
         var9 = 1;
      }

      this.method_32(var1, (byte) var9, var2, var3, var4, var5, var1.getClipX(), var1.getClipY(), var1.getClipWidth(), var1.getClipHeight());
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, byte, java.lang.String, int, int, int, int, int, int, int) void
   private void method_32(Gfx var1, byte var2, String var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      if (var3 != null) {
         var1.setClip(var7, var8, var9, var10);
         if ((var6 & 32) != 0) {
            var5 -= this.field_77[var2];
         } else if ((var6 & 64) != 0) {
            var5 -= this.field_78[var2];
         } else if ((var6 & 2) != 0) {
            var5 -= this.field_77[var2] / 2;
         }

         if ((var6 & 8) != 0) {
            var4 -= this.method_34(var3, var2);
         } else if ((var6 & 1) != 0) {
            var4 -= this.method_34(var3, var2) / 2;
         }

         if (var2 == 0) {
            var1.drawString(var3, var4, var5, 20);
         } else {
            var6 = var3.length();
            int var12 = 0;
            long[] var13 = this.field_80[var2];

            for (int var14 = 0; var14 < var6; var14++) {
               int var11 = var3.charAt(var14);
               int var15 = var4 + var12;
               if (var11 >= 8220) {
                  var11 = 1105 + (var11 - 8220);
               }

               int var22;
               if (var11 < 1108 && var11 >= 0) {
                  var22 = this.field_79[var11];
               } else {
                  var22 = -1;
               }

               if (var22 == -1) {
                  var22 = this.field_79[63];
               }

               long var19;
               var22 = (int)((var19 = var13[var22]) >> 24 & 255L);
               int var17 = (int)(var19 >> 16 & 255L);
               int var18 = (byte)((int)(var19 & 255L));
               int var16 = var5 - var17 + var18 + this.field_78[var2];
               var1.setClip(var15, var16, var22, var17);
               if (var2 == 1 || var2 == 4) {
                  var1.fillRect(var15, var16, var22, var17);
               }

               var1.drawImage(this.field_83[var2], var15 - (int)(var19 >> 40 & 255L), var16 - (int)(var19 >> 32 & 255L), 20);
               var22 += (byte)((int)(var19 >> 8 & 255L));
               var12 += var22;
            }

            var1.setClip(var7, var8, var9, var10);
         }
      }
   }

   // $VF: renamed from: a (byte) int
   public final int method_33(byte var1) {
      return this.field_77[var1];
   }

   // $VF: renamed from: a (java.lang.String, byte) int
   public final int method_34(String var1, byte var2) {
      if (var1 == null) {
         return 0;
      } else if (var2 == 0) {
         return this.field_82.stringWidth(var1);
      } else {
         int var3 = var1.length();
         int var4 = 0;
         long[] var11 = this.field_80[var2];

         for (int var6 = 0; var6 < var3; var6++) {
            int var5;
            if ((var5 = var1.charAt(var6)) >= 8220) {
               var5 = 1105 + (var5 - 8220);
            }

            int var12;
            if (var5 < 1108 && var5 >= 0) {
               var12 = this.field_79[var5];
            } else {
               var12 = -1;
            }

            if (var12 == -1) {
               var12 = this.field_79[63];
            }

            long var9;
            var12 = (int)((var9 = var11[var12]) >> 24 & 255L) + (byte)((int)(var9 >> 8 & 255L));
            var4 += var12;
         }

         return var4;
      }
   }

   // $VF: renamed from: a (char) int
   public final int method_35(char var1) {
      return this.method_36(var1, (byte)1);
   }

   // $VF: renamed from: a (char, byte) int
   public final int method_36(char var1, byte var2) {
      if (var1 >= 8220) {
         var1 = (char)(1105 + (var1 - 8220));
      }

      int var6;
      if (var1 < 1108 && var1 >= 0) {
         var6 = this.field_79[var1];
      } else {
         var6 = -1;
      }

      long var4;
      return var6 == -1 ? 0 : (int)((var4 = this.field_80[1][var6]) >> 24 & 255L) + (byte)((int)(var4 >> 8 & 255L));
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, byte, char, int, int) int
   public final int method_37(Gfx var1, byte var2, char var3, int var4, int var5) {
      if (var2 == 0) {
         var1.drawChar((char)var3, var4, var5, 0);
         return var1.getFont().charWidth((char)var3);
      } else {
         if (var3 >= 8220) {
            var3 = (char)(1105 + (var3 - 8220));
         }

         int var11;
         if (var3 < 1108 && var3 >= 0) {
            var11 = this.field_79[var3];
         } else {
            var11 = -1;
         }

         if (var11 == -1) {
            var11 = this.field_79[63];
         }

         long var7;
         int width = (int)((var7 = this.field_80[var2][var11]) >> 24 & 255L);
         int var6 = (int)(var7 >> 16 & 255L);
         int var9 = (byte)((int)(var7 & 255L));
         var5 = var5 - var6 + var9 + this.field_78[var2];
         var1.setClip(var4, var5, width, var6);
         if (var2 == 1 || var2 == 4) {
            var1.fillRect(var4, var5, width, var6);
         }

         var1.drawImage(this.field_83[var2], var4 - (int)(var7 >> 40 & 255L), var5 - (int)(var7 >> 32 & 255L), 20);
         int var10;
         return var10 = width + (byte)((int)(var7 >> 8 & 255L));
      }
   }

   // $VF: renamed from: a (int) byte
   public final byte method_38(int var1) {
      if (var1 == 4) {
         return this.method_39();
      } else if (var1 == 2) {
         return this.method_40(var1, 16776960, 16776960);
      } else {
         return var1 == 3 ? this.method_40(var1, 16711680, 16711680) : this.method_40(var1, -16777216, -16777216);
      }
   }

   // $VF: renamed from: a () byte
   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private byte method_39() {
      this.field_83[1].getWidth();
      this.field_83[1].getHeight();
      Img var1 = method_43();
      DataInputStream var2 = null;
      int var3 = this.field_84;
      boolean var15 = false /* VF: Semaphore variable */;

      label93: {
         try {
            var15 = true;
            this.field_83[this.field_84] = var1;
            var1 = null;
            InputStream var4;
            byte[] var22 = new byte[(var4 = this.getClass().getResourceAsStream("/font.bin")).available()];
            var4.read(var22);
            var4.close();
            var2 = new DataInputStream(new ByteArrayInputStream(var22));
            this.field_81[this.field_84] = -16777216;
            this.field_77[this.field_84] = var2.readByte() << 1;
            this.field_78[this.field_84] = var2.readByte() << 1;
            int var23 = var2.readUnsignedByte();
            this.field_80[this.field_84] = new long[var23];

            for (int var24 = 0; var24 < var23; var24++) {
               long var8;
               int var5 = (short)((int)((var8 = var2.readLong()) >> 48));
               var8 += var8;
               if (var5 >= 8220) {
                  var5 = 1105 + (var5 - 8220);
               }

               this.field_79[var5] = (byte)var24;
               this.field_80[this.field_84][var24] = var8;
            }

            this.field_84++;
            var15 = false;
            break label93;
         } catch (IOException var19) {
            var15 = false;
         } finally {
            if (var15) {
               try {
                  if (var2 != null) {
                     var2.close();
                  }
               } catch (IOException var16) {
               }
            }
         }

         try {
            if (var2 != null) {
               var2.close();
               return (byte) var3;
            }
         } catch (IOException var17) {
         }

         return (byte) var3;
      }

      try {
         var2.close();
      } catch (IOException var18) {
      }

      return (byte) var3;
   }

   // $VF: renamed from: a (int, int, int) byte
   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private byte method_40(int var1, int var2, int var3) {
      DataInputStream var25 = null;
      int var4 = this.field_84;
      boolean var17 = false /* VF: Semaphore variable */;

      label114: {
         try {
            var17 = true;
            Img var5 = null;
            if (var1 == 1) {
               var5 = Img.createImage("/t0.png");
            } else if (var1 == 3) {
               var5 = Img.createImage("/t0_red.png");
            } else if (var1 == 2) {
               var5 = Img.createImage("/t0_yellow.png");
            }

            if (var1 == 1) {
               var5 = Img.createImage("/t0_double.png");
            } else {
               var5 = method_41(var5);
            }

            this.field_83[this.field_84] = var5;
            InputStream var23 = this.getClass().getResourceAsStream("/font.bin");
            var25 = new DataInputStream(var23);
            this.field_81[this.field_84] = var3;
            this.field_77[this.field_84] = var25.readByte();
            this.field_78[this.field_84] = var25.readByte();
            this.field_77[this.field_84] = this.field_77[this.field_84] << 1;
            this.field_78[this.field_84] = this.field_78[this.field_84] << 1;
            var1 = var25.readUnsignedByte();
            this.field_80[this.field_84] = new long[var1];

            for (int var26 = 0; var26 < var1; var26++) {
               long var10;
               int var28 = (short)((int)((var10 = var25.readLong()) >> 48));
               var10 += var10;
               if (var28 >= 8220) {
                  var28 = 1105 + (var28 - 8220);
               }

               this.field_79[var28] = (byte)var26;
               this.field_80[this.field_84][var26] = var10;
            }

            this.field_84++;
            var17 = false;
            break label114;
         } catch (IOException var21) {
            var17 = false;
         } finally {
            if (var17) {
               try {
                  if (var25 != null) {
                     var25.close();
                  }
               } catch (IOException var18) {
               }
            }
         }

         try {
            if (var25 != null) {
               var25.close();
               return (byte) var4;
            }
         } catch (IOException var19) {
         }

         return (byte) var4;
      }

      try {
         var25.close();
      } catch (IOException var20) {
      }

      return (byte) var4;
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Img) javax.microedition.lcdui.Img
   private static Img method_41(Img var0) {
      int[] var1 = null;
      int var2 = var0.getWidth();
      int var3 = var0.getHeight();

      try {
         int[] var4 = new int[var2 * var3];
         var0.getRGB(var4, 0, var2, 0, 0, var2, var3);
         var2 <<= 1;
         var3 <<= 1;
         Img.createImage(var2, var3);
         var1 = new int[var2 * var3];
         int var8 = 0;

         for (int var5 = 0; var5 < var3; var5 += 2) {
            for (int var6 = 0; var6 < var2; var6 += 2) {
               var1[var5 * var2 + var6] = var4[var8];
               var1[var5 * var2 + var6 + 1] = var4[var8];
               var1[(var5 + 1) * var2 + var6] = var4[var8];
               var1[(var5 + 1) * var2 + var6 + 1] = var4[var8];
               var8++;
            }
         }
      } catch (Exception var7) {
      }

      return Img.createRGBImage(var1, var2, var3, true);
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, int, int, int) void
   public final void method_42(Gfx var1, int var2, int var3, int var4) {
      char var5 = '?';
      switch (var4) {
         case 12:
            var5 = '<';
            break;
         case 13:
            var5 = '>';
            break;
         case 52:
            var5 = '-';
            break;
         case 62:
            var5 = 710;
            break;
         case 63:
            var5 = 711;
            break;
         default:
            if (var4 >= 0 && var4 < 10) {
               var5 = (char)(var4 + 48);
            }
      }

      int var9;
      if (var5 < 1108 && var5 >= 0) {
         var9 = this.field_79[var5];
      } else {
         var9 = -1;
      }

      if (var9 != -1) {
         long var7;
         var9 = (int)((var7 = this.field_80[1][var9]) >> 24 & 255L);
         int var6 = (int)(var7 >> 16 & 255L);
         int var11 = (byte)((int)(var7 & 255L));
         int var12 = var3 - var6 + var11 + this.field_78[1];
         var1.setClip(var2, var12, var9, var6);
         var1.fillRect(var2, var12, var9, var6);
         this.method_37(var1, (byte)1, var5, var2, var3);
      }
   }

   // $VF: renamed from: a () javax.microedition.lcdui.Img
   private static Img method_43() {
      try {
         return Img.createImage("/t0_double.png");
      } catch (Exception var0) {
         return null;
      }
   }
}
