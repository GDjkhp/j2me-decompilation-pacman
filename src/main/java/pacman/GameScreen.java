package pacman;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import pacman.platform.Screen;
import pacman.platform.Command;
import pacman.platform.Display;
import pacman.platform.Fnt;
import pacman.platform.Gfx;
import pacman.platform.Img;
import pacman.net.LanDiscovery;
import pacman.net.LanHost;
import pacman.net.LanLink;
import pacman.net.LanListener;
import pacman.platform.PersistentStore;

// $VF: renamed from: g
public final class GameScreen extends Screen implements Runnable {
   // $VF: renamed from: c boolean
   private static boolean field_119;
   // $VF: renamed from: d boolean
   private static boolean field_120;
   // $VF: renamed from: e boolean
   private static boolean field_121;
   // $VF: renamed from: f boolean
   private boolean field_122;
   // $VF: renamed from: a a
   private Localization field_123;
   // $VF: renamed from: a java.lang.String[]
   private String[] field_125 = new String[]{"GAME IS SUSPENDED.", "PLEASE REVERT PHONE DISPLAY TO DEFAULT ORIENTATION TO RESUME."};
   // $VF: renamed from: b java.lang.String[]
   private static String[] field_126 = new String[]{"%C%*-----", "%C%-*----", "%C%--*---", "%C%---*--", "%C%----*-", "%C%-----*"};
   // $VF: renamed from: a char[]
   private static char[] field_127 = new char[]{'1', 'U', 'P'};
   // $VF: renamed from: b char[]
   private static char[] field_128 = new char[]{'(', 'C', 'O', 'M', ')'};
   // $VF: renamed from: a m
   private SoundPlayer field_129;
   // $VF: renamed from: a int
   private int field_130 = 2;
   // $VF: renamed from: g boolean
   private boolean field_131;
   // $VF: renamed from: h boolean
   private boolean field_132 = true;
   // $VF: renamed from: i boolean
   // $VF: renamed from: b int
   private int field_134;
   // $VF: renamed from: a java.lang.String
   private String field_135;
   // $VF: renamed from: j boolean
   private boolean field_136;
   // $VF: renamed from: a javax.microedition.lcdui.Command
   private Command field_137;
   // $VF: renamed from: b javax.microedition.lcdui.Command
   private Command field_138;
   // $VF: renamed from: c javax.microedition.lcdui.Command
   private Command field_139;
   // $VF: renamed from: d javax.microedition.lcdui.Command
   private Command field_140;
   // $VF: renamed from: e javax.microedition.lcdui.Command
   private Command field_141;
   // $VF: renamed from: f javax.microedition.lcdui.Command
   private Command field_142 = new Command(" ", 1, 2);
   // $VF: renamed from: g javax.microedition.lcdui.Command
   private Command field_143;
   // $VF: renamed from: h javax.microedition.lcdui.Command
   private Command field_144;
   // $VF: renamed from: i javax.microedition.lcdui.Command
   private Command field_145;
   // $VF: renamed from: j javax.microedition.lcdui.Command
   private Command field_146;
   // $VF: renamed from: k javax.microedition.lcdui.Command
   private Command field_147;
   // $VF: renamed from: b byte[]
   private static byte[] field_148 = new byte[12];
   // $VF: renamed from: a short[]
   static final short[] field_149 = new short[396];
   // $VF: renamed from: c int
   private static int field_150;
   // $VF: renamed from: d int
   private static int field_151;
   // $VF: renamed from: e int
   private static int field_152;
   // $VF: renamed from: f int
   private static int field_153;
   // $VF: renamed from: g int
   private static int field_154;
   // $VF: renamed from: a short[][]
   private static short[][] field_155 = new short[11][];
   // $VF: renamed from: h int
   private int field_156;
   // $VF: renamed from: i int
   private int field_157;
   // $VF: renamed from: j int
   private int field_158;
   // $VF: renamed from: b short[]
   private static short[] field_159 = new short[]{
      0,
      32,
      16,
      16,
      16,
      32,
      16,
      16,
      32,
      32,
      16,
      16,
      16,
      32,
      16,
      16,
      0,
      0,
      32,
      32,
      32,
      0,
      32,
      32,
      64,
      0,
      32,
      32,
      32,
      0,
      32,
      32,
      96,
      0,
      16,
      16,
      112,
      0,
      16,
      16,
      128,
      0,
      16,
      16,
      144,
      0,
      16,
      16,
      128,
      16,
      16,
      16,
      144,
      16,
      16,
      16,
      96,
      16,
      16,
      16,
      112,
      16,
      16,
      16,
      48,
      41,
      22,
      11,
      70,
      41,
      22,
      11,
      48,
      32,
      13,
      9,
      61,
      32,
      13,
      9,
      74,
      32,
      13,
      9,
      87,
      32,
      13,
      9,
      100,
      32,
      13,
      9,
      113,
      32,
      13,
      9
   };
   // $VF: renamed from: c short[]
   private static short[] field_160 = new short[]{0, 0, 240, 107, 0, 107, 140, 16, 140, 107, 12, 7};
   // $VF: renamed from: d short[]
   private static short[] field_161 = new short[8];
   // $VF: renamed from: e short[]
   private static short[] field_162 = new short[]{
      64,
      33,
      8,
      7,
      72,
      33,
      8,
      7,
      80,
      33,
      8,
      7,
      88,
      34,
      8,
      7,
      96,
      34,
      8,
      7,
      104,
      34,
      7,
      7,
      103,
      20,
      14,
      14,
      117,
      20,
      14,
      14,
      51,
      20,
      13,
      13,
      64,
      20,
      13,
      13,
      77,
      20,
      13,
      13,
      111,
      0,
      20,
      20,
      51,
      20,
      13,
      13,
      64,
      20,
      13,
      13,
      77,
      20,
      13,
      13,
      0,
      0,
      51,
      30,
      103,
      20,
      14,
      14,
      0,
      31,
      8,
      8,
      8,
      31,
      8,
      8,
      16,
      31,
      8,
      8,
      24,
      31,
      8,
      8,
      32,
      31,
      8,
      8,
      40,
      31,
      8,
      8,
      48,
      33,
      8,
      8,
      56,
      33,
      8,
      8,
      111,
      34,
      7,
      7,
      118,
      34,
      13,
      7,
      4,
      41,
      100,
      104,
      0,
      145,
      112,
      112,
      114,
      41,
      17,
      11
   };
   // $VF: renamed from: b int[]
   private static int[] field_163 = new int[]{12500, 12000, 12000};
   // $VF: renamed from: c byte[]
   private static byte[] field_164 = new byte[42];
   // $VF: renamed from: d byte[]
   private static byte[] field_165;
   // $VF: renamed from: a byte[][]
   private byte[][] field_166;
   // $VF: renamed from: b byte[][]
   private byte[][] field_167 = new byte[21][8];
   // $VF: renamed from: c byte[][]
   private byte[][] field_168 = new byte[21][];
   // $VF: renamed from: d byte[][]
   private byte[][] field_169 = new byte[11][4];
   // $VF: renamed from: e byte[][]
   private byte[][] field_170 = new byte[7][8];
   // $VF: renamed from: e byte[]
   private byte[] field_171;
   // $VF: renamed from: f byte[]
   private byte[] field_172;
   // $VF: renamed from: a int[]
   static int[] field_173 = new int[99];
   // $VF: renamed from: a int[][]
   private int[][] field_174 = new int[4][16];
   // $VF: renamed from: k int
   private int field_175;
   // $VF: renamed from: a long
   private long field_176;
   // $VF: renamed from: l int
   private int field_177;
   // $VF: renamed from: m int
   private int field_178;
   // $VF: renamed from: a Pacman
   public Pacman field_179;
   // $VF: renamed from: c int[]
   private int[] field_180;
   // $VF: renamed from: b java.lang.String
   private String field_181;
   // $VF: renamed from: c java.lang.String
   private String field_182;
   // $VF: renamed from: a javax.microedition.lcdui.Gfx
   private Gfx field_183;
   // $VF: renamed from: b javax.microedition.lcdui.Gfx
   private Gfx field_184;
   // $VF: renamed from: a javax.microedition.lcdui.Img
   private Img field_185;
   // $VF: renamed from: c javax.microedition.lcdui.Gfx
   private Gfx field_186;
   // $VF: renamed from: b javax.microedition.lcdui.Img
   private Img field_187;
   // $VF: renamed from: c javax.microedition.lcdui.Img
   private Img field_188;
   // $VF: renamed from: d javax.microedition.lcdui.Img
   private Img field_189;
   // $VF: renamed from: e javax.microedition.lcdui.Img
   private Img field_190;
   // $VF: renamed from: f javax.microedition.lcdui.Img
   private Img field_191;
   // $VF: renamed from: g javax.microedition.lcdui.Img
   private Img field_192;
   // $VF: renamed from: h javax.microedition.lcdui.Img
   private Img field_193;
   // $VF: renamed from: c java.lang.String[]
   private String[] field_194 = new String[]{"BNG_logo.png"};
   // $VF: renamed from: d int[]
   private int[] field_195 = new int[]{800, 800, 800};
   // $VF: renamed from: e int[]
   private int[] field_196 = new int[]{1000, 1000, 1000};
   // $VF: renamed from: f int[]
   private int[] field_197 = new int[]{16777215, 16777215, 16777215};
   // $VF: renamed from: g int[]
   private int[] field_198 = new int[]{0, 0, 5, 0, 0, 0};
   // $VF: renamed from: a boolean[]
   private boolean[] field_199 = new boolean[]{true, true, true};
   // $VF: renamed from: a javax.microedition.lcdui.Img[]
   private Img[] field_200;
   // $VF: renamed from: n int
   private int field_201;
   // $VF: renamed from: o int
   private int field_202;
   // $VF: renamed from: p int
   private int field_203;
   // $VF: renamed from: q int
   private int field_204;
   // $VF: renamed from: r int
   private int field_205;
   // $VF: renamed from: b javax.microedition.lcdui.Img[]
   private Img[] field_206 = new Img[4];
   // $VF: renamed from: s int
   private int field_207 = 0;
   // $VF: renamed from: t int
   private int field_208;
   // $VF: renamed from: u int
   private int field_209;
   // $VF: renamed from: a javax.microedition.lcdui.Fnt
   private Fnt field_210;
   // $VF: renamed from: a java.util.Random
   private Random field_211;
   // $VF: renamed from: g byte[]
   private byte[] field_212;
   // $VF: renamed from: h int[]
   private int[] field_213;
   // $VF: renamed from: i int[]
   private int[] field_214;
   // $VF: renamed from: b boolean[]
   private boolean[] field_215;
   // $VF: renamed from: v int
   private int field_216;
   // $VF: renamed from: d java.lang.String
   private String field_217;
   // $VF: renamed from: e java.lang.String
   private String field_218;
   // $VF: renamed from: w int
   private int field_219;
   // $VF: renamed from: x int
   private int field_220;
   // $VF: renamed from: y int
   private int field_221;
   // $VF: renamed from: z int
   private int field_222;
   // $VF: renamed from: A int
   private int field_223;
   // $VF: renamed from: j int[]
   private int[] field_224;
   // $VF: renamed from: B int
   private int field_225;
   // $VF: renamed from: a byte[]
   byte[] field_226;
   // $VF: renamed from: h byte[]
   private byte[] field_227;
   // $VF: renamed from: C int
   // $VF: renamed from: c char[]
   private char[] field_231;
   // $VF: renamed from: a boolean
   volatile boolean field_232;
   // $VF: renamed from: a javax.bluetooth.RemoteDevice[]
   private LanDiscovery field_233;
   // $VF: renamed from: D int
   // $VF: renamed from: a javax.bluetooth.L2CAPConnectionNotifier
   LanListener field_235;
   // $VF: renamed from: a javax.bluetooth.L2CAPConnection[]
   LanLink[] field_236;
   // $VF: renamed from: f java.lang.String
   private String field_237;
   // $VF: renamed from: a javax.bluetooth.ServiceRecord[]
   private LanHost[] field_238;
   // $VF: renamed from: E int
   private int field_239;
   // $VF: renamed from: G int
   private int field_241;
   // $VF: renamed from: H int
   private int field_242;
   // $VF: renamed from: I int
   private int field_243;
   // $VF: renamed from: J int
   private int field_244;
   // $VF: renamed from: f byte[][]
   private byte[][] field_245;
   // $VF: renamed from: k boolean
   private boolean field_246;
   // $VF: renamed from: l boolean
   private boolean field_247;
   // $VF: renamed from: K int
   private int field_248;
   // $VF: renamed from: m boolean
   private boolean field_249;
   // $VF: renamed from: L int
   private int field_250;
   // $VF: renamed from: M int
   private int field_251;
   // $VF: renamed from: N int
   private int field_252;
   // $VF: renamed from: O int
   private int field_253;
   // $VF: renamed from: b boolean
   public boolean field_254;
   // $VF: renamed from: b long
   private long field_255;
   // $VF: renamed from: l int[]
   private int[] field_256;
   // $VF: renamed from: P int
   private int field_257;
   // $VF: renamed from: Q int
   private int field_258;
   // $VF: renamed from: n boolean
   private boolean field_259;
   // $VF: renamed from: o boolean
   private boolean field_260;
   // $VF: renamed from: m int[]
   private int[] field_261;
   // $VF: renamed from: p boolean
   private boolean field_262;
   // $VF: renamed from: R int
   private int field_263;
   // $VF: renamed from: c long
   private long field_264;
   // $VF: renamed from: q boolean
   private boolean field_265;
   // $VF: renamed from: n int[]
   private int[] field_266;
   // $VF: renamed from: o int[]
   private int[] field_267;
   // $VF: renamed from: r boolean
   private boolean field_268;
   // $VF: renamed from: S int
   private int field_269;
   // $VF: renamed from: T int
   private int field_270;
   // $VF: renamed from: b java.util.Vector
   private Vector field_271;
   // $VF: renamed from: U int
   private int field_272;
   // $VF: renamed from: V int
   private int field_273;
   // $VF: renamed from: W int
   private int field_274;
   // $VF: renamed from: s boolean
   private boolean field_275;
   // $VF: renamed from: t boolean
   private boolean field_276;
   // $VF: renamed from: u boolean
   private boolean field_277;
   // $VF: renamed from: a java.util.Vector
   public Vector field_278;
   // $VF: renamed from: d java.lang.String[]
   private String[] field_279;
   // $VF: renamed from: e java.lang.String[]
   private String[] field_280;
   // $VF: renamed from: X int
   private int field_281;
   // $VF: renamed from: Y int
   private int field_282;
   // $VF: renamed from: f java.lang.String[]
   private String[] field_283;
   // $VF: renamed from: Z int
   private int field_284;
   // $VF: renamed from: g java.lang.String
   private String field_285;
   // $VF: renamed from: a byte
   private byte field_286;
   // $VF: renamed from: v boolean
   private boolean field_287;

   // $VF: renamed from: a (int) boolean
   private boolean method_55(int var1) {
      int var2 = -this.field_205;
      int var3;
      if ((var3 = this.field_194.length) == 0) {
         return false;
      } else {
         for (int var4 = 0; var4 < var3; var4++) {
            this.field_201 = var4;
            if (var2 + this.field_195[var4] > var1) {
               this.field_202 = 0;
               break;
            }

            if ((var2 += this.field_195[var4]) + this.field_196[var4] > var1) {
               this.field_202 = 1;
               break;
            }

            if ((var2 += this.field_196[var4]) + this.field_195[var4] > var1) {
               this.field_202 = 2;
               break;
            }

            if (var4 != var3 - 1) {
               var2 += this.field_195[var4];
            } else if (var2 + (this.field_195[var4] << 1) + this.field_196[var4] < var1) {
               return false;
            }
         }

         this.field_204 = var1 - var2;
         if (this.field_201 == var3 - 1 && this.field_202 == 2 && this.field_204 > this.field_195[this.field_201]) {
            return false;
         } else {
            if (this.field_200[this.field_201] == null) {
               if (this.field_201 != 0) {
                  this.field_200[this.field_201] = null;
                  System.gc();
               }

               try {
                  this.field_200[this.field_201] = Img.createImage("/" + this.field_194[this.field_201]);
               } catch (Exception var5) {
               }
            }

            if (this.field_202 == 0) {
               this.field_203 = (var1 - var2) * 255 / this.field_195[this.field_201];
            }

            if (this.field_202 == 1) {
               this.field_203 = 255;
            }

            if (this.field_202 == 2) {
               this.field_203 = 255 - (var1 - var2) * 255 / this.field_195[this.field_201];
            }

            return true;
         }
      }
   }

   // $VF: renamed from: a (byte[]) void
   private void method_56(byte[] var1) {
      var1[0] = (byte)field_173[1];
      method_59(field_173[4], var1, 1, 2);
      method_59(field_173[5], var1, 3, 2);
      var1[5] = (byte)field_173[15];
      method_59(field_173[11], var1, 6, 3);
      var1[9] = (byte)field_173[23];
      var1[9] = (byte)(var1[9] | field_173[28] << 2);
      var1[9] = (byte)(var1[9] | field_173[37] << 4);
      var1[9] = (byte)(var1[9] | (field_173[38] & 3) << 5);
      var1[10] = (byte)field_173[25];
      method_59(field_173[85], var1, 11, 2);
      var1[13] = (byte)field_173[72];
      var1[13] = (byte)(var1[13] | field_173[73] << 1);
      var1[13] = (byte)(var1[13] | field_173[75] << 2);
      method_59(field_173[35], var1, 14, 4);
      int var2 = 18;
      int var3 = 0;
      int var4 = 0;

      for (int var6 = 0; var6 < field_154; var6++) {
         int var7;
         do {
            int var5 = field_165[var4 >> 1];
            if ((var4 & 1) == 0) {
               var5 >>= 4;
            }

            var7 = var5 & 15;
            var4++;
         } while (var7 != 1 && var7 != 2 && var7 != 7);

         if ((var7 = this.field_180[var4 - 1] & 15) == 1 || var7 == 2) {
            var3 |= 128;
         }

         if (var6 == field_154 - 1 && (field_154 - 1 & 7) != 7) {
            var3 >>>= 7 - (field_154 - 1 & 7);
         }

         if ((var6 & 7) != 7 && var6 != field_154 - 1) {
            var3 >>>= 1;
         } else {
            var1[var2++] = (byte)(0xFF & var3);
            var3 = 0;
         }
      }
   }

   // $VF: renamed from: f () void
   private void method_57() {
      this.method_56(this.field_245[this.field_220]);

      for (int var1 = this.field_219 - 1; var1 >= 0; var1--) {
         this.field_220++;
         if (this.field_220 >= this.field_219) {
            this.field_220 = 0;
         }

         byte[] var3 = this.field_245[this.field_220];
         GameScreen var2 = this;
         field_173[1] = var3[0];
         field_173[4] = method_58(var3, 1, 2);
         field_173[5] = method_58(var3, 3, 2);
         field_173[15] = var3[5];
         field_173[11] = method_58(var3, 6, 3);
         field_173[23] = var3[9] & 3;
         field_173[28] = var3[9] >> 2 & 3;
         field_173[37] = var3[9] >> 4 & 1;
         field_173[38] = var3[9] >> 5 & 3;
         field_173[25] = 255 & var3[10];
         field_173[85] = method_58(var3, 11, 2);
         field_173[72] = var3[13] & 1;
         field_173[73] = var3[13] >> 1 & 1;
         field_173[75] = var3[13] >> 2 & 1;
         field_173[35] = method_58(var3, 14, 4);
         int var4 = 18;
         int var5 = 0;
         int var6 = 0;

         for (int var9 = 0; var9 < field_154; var9++) {
            if ((var9 & 7) == 0) {
               var5 = var3[var4++];
            } else {
               var5 >>>= 1;
            }

            int var10;
            do {
               int var7 = field_165[var6 >> 1];
               if ((var6 & 1) == 0) {
                  var7 >>= 4;
               }

               var10 = var7 & 15;
               var6++;
            } while (var10 != 1 && var10 != 2 && var10 != 7);

            int var8 = var2.field_180[var6 - 1] & 15;
            if ((var5 & 1) != 0) {
               if (var10 == 2) {
                  var8 |= 2;
               } else {
                  var8 |= 1;
               }
            } else {
               var8 &= -4;
            }

            var2.field_180[var6 - 1] = var2.field_180[var6 - 1] & -16 | var8;
         }

         field_173[34] = field_173[25];
         if (field_173[34] > 20) {
            field_173[34] = 20;
         }

         var2.field_212 = var2.field_166[field_173[34]];
         field_173[0] = 2;
         MenuUi.field_49 = 1;
         if (field_173[1] != 26) {
            break;
         }
      }

      this.method_95();
   }

   // $VF: renamed from: a (byte[], int, int) int
   private static int method_58(byte[] var0, int var1, int var2) {
      int var3 = 0;

      while (var2 > 0) {
         int var4;
         var3 = (var4 = var3 << 8) | 255 & var0[var1++];
         var2--;
      }

      return var3;
   }

   // $VF: renamed from: a (int, byte[], int, int) void
   private static void method_59(int var0, byte[] var1, int var2, int var3) {
      for (int var4 = var2 + var3; var3 > 0; var3--) {
         var4--;
         var1[var4] = (byte)var0;
         var0 >>>= 8;
      }
   }

   public final void showNotify() {
      if (System.currentTimeMillis() < this.field_176) {
         this.field_176 = System.currentTimeMillis();
      }

      this.field_246 = true;
      MenuUi.field_49 = 3;
      this.field_265 = true;
   }

   public final void hideNotify() {
      this.field_246 = true;
      this.field_265 = false;
   }

   /** Saves the persistent game data (options, personal bests, nickname) to the save file. */
   private void method_60() {
      try {
         ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         DataOutputStream out = new DataOutputStream(bytes);
         out.write(field_164, 0, 42);
         out.writeUTF(this.field_285 == null ? "" : this.field_285);
         byte[] data = bytes.toByteArray();
         PersistentStore store = PersistentStore.open("vs_pacman", true);
         store.setRecord(1, data, 0, data.length);
         store.close();
      } catch (Exception var1) {
      }
   }

   public GameScreen(Pacman var1) {
      Fnt.getFont(64, 1, 0);
      this.field_210 = Fnt.getFont(64, 0, 8);
      this.field_213 = new int[10];
      this.field_214 = new int[]{-65536, -18210, -16711714, -18361, -16712192};
      int[] var10000 = new int[]{-16776968, -3663618, -124728, -2090984};
      this.field_215 = new boolean[4];
      this.field_231 = new char[35];
      this.field_243 = -1;
      this.field_244 = -1;
      this.field_245 = new byte[5][];
      this.field_246 = false;
      this.field_250 = -1;
      this.field_251 = -1;
      this.field_252 = -1;
      this.field_253 = -1;
      this.field_255 = 0L;
      this.field_257 = 0;
      this.field_261 = new int[3];
      this.field_262 = false;
      this.field_264 = 0L;
      this.field_265 = true;
      this.field_266 = new int[4];
      this.field_267 = new int[4];
      this.field_268 = false;
      this.field_271 = new Vector(10, 1);
      this.field_272 = 9999;
      this.field_276 = false;
      this.field_277 = false;
      this.field_278 = new Vector(5, 1);
      this.field_280 = new String[]{
         "en",
         "ENGLISH",
         "LANGUAGE",
         "de",
         "DEUTSCH",
         "SPRACHE",
         "es",
         "ESPA\u00d1OL",
         "IDIOMA",
         "fr",
         "FRAN\u00c7AIS",
         "LANGUE",
         "it",
         "ITALIANO",
         "LINGUA",
         "nl",
         "NEDERLANDS",
         "TAAL",
         "da",
         "DANSK",
         "?!?!",
         "pt",
         "PORTUGUESE",
         "IDIOMA"
      };
      this.field_281 = 9999;
      this.field_282 = -999;
      this.field_283 = null;
      this.field_284 = 0;
      this.field_285 = "";
      this.field_286 = 0;
      this.field_287 = false;
      this.setFullScreenMode(true);
      field_173[3] = -2;
      this.field_179 = var1;
   }

   // $VF: renamed from: a (int, int, int, int) int
   private static int method_61(int var0, int var1, int var2, int var3) {
      if (var0 == var2 && var1 == var3) {
         return 9999;
      } else if (var0 >= var2 && var1 <= var3 && var0 - var2 >= var3 - var1) {
         return -4;
      } else if (var0 >= var2 && var1 >= var3 && var0 - var2 >= var1 - var3) {
         return -4;
      } else if (var0 >= var2 && var1 <= var3 && var0 - var2 <= var3 - var1) {
         return -1;
      } else if (var0 >= var2 && var1 >= var3 && var0 - var2 <= var1 - var3) {
         return -2;
      } else if (var0 <= var2 && var1 <= var3 && var2 - var0 >= var3 - var1) {
         return -3;
      } else if (var0 <= var2 && var1 >= var3 && var2 - var0 >= var1 - var3) {
         return -3;
      } else if (var0 <= var2 && var1 <= var3 && var2 - var0 <= var3 - var1) {
         return -1;
      } else {
         return var0 <= var2 && var1 >= var3 && var2 - var0 <= var1 - var3 ? -2 : 9999;
      }
   }

   // $VF: renamed from: a (int, int, int, int, int, int) void
   public final void method_62(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (this.field_256 == null) {
         this.field_256 = new int[192];
      }

      this.field_256[this.field_257 * 6] = var1;
      this.field_256[this.field_257 * 6 + 1] = var2;
      this.field_256[this.field_257 * 6 + 2] = var3;
      this.field_256[this.field_257 * 6 + 3] = var4;
      this.field_256[this.field_257 * 6 + 4] = var5;
      this.field_256[this.field_257 * 6 + 5] = var6;
      this.field_257++;
   }

   // $VF: renamed from: h () void
   private void method_63() {
      if (this.field_254 && this.field_255 != 0L && System.currentTimeMillis() - this.field_255 > 500L) {
         this.pointerReleased(this.field_250, this.field_251);
      }

      if (this.field_249) {
         this.method_64();
      }
   }

   // $VF: renamed from: a () boolean
   private boolean method_64() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.field_257; var2++) {
         int var3 = this.field_256[var2 * 6];
         int var4 = this.field_256[var2 * 6 + 1];
         int var5 = this.field_256[var2 * 6 + 2];
         int var6 = this.field_256[var2 * 6 + 3];
         int var7 = this.field_256[var2 * 6 + 4];
         int var8;
         int var9 = (var8 = this.field_256[var2 * 6 + 5]) & 2147483647;
         boolean var10 = (var8 & -2147483648) != 0;
         if (this.field_250 > var3 && this.field_250 < var3 + var5 && this.field_251 > var4 && this.field_251 < var4 + var6) {
            label67:
            switch (var7) {
               case 0:
                  switch (var9) {
                     case 0:
                        MessageScreens.method_27(2);
                        this.field_258 = MessageScreens.field_68;
                        break label67;
                     case 1:
                        MessageScreens.method_27(64);
                        this.field_258 = MessageScreens.field_68;
                        break label67;
                     default:
                        var1 = var9 - 2;
                        if (MenuUi.field_30 != var1) {
                           MenuUi.field_30 = var1;
                        } else {
                           this.keyPressed(-5);
                           this.keyReleased(-5);
                        }
                        break label67;
                  }
               case 1:
                  switch (var9) {
                     case 0:
                        this.keyPressed(-6);
                        break label67;
                     case 1:
                        this.keyPressed(-7);
                     default:
                        break label67;
                  }
               case 2:
                  field_119 = true;
                  break;
               case 3:
                  if (this.field_242 != var9) {
                     var3 = this.field_242 % 2;
                     var4 = this.field_242 / 2;
                     var5 = var9 % 2;
                     var1 = var9 / 2;
                     if (var5 > var3) {
                        this.keyPressed(-4);
                        this.keyReleased(-4);
                     }

                     if (var5 < var3) {
                        this.keyPressed(-3);
                        this.keyReleased(-3);
                     }

                     if (var3 != var5 && var4 != var1) {
                        this.method_85();
                     }

                     if (var1 > var4) {
                        this.keyPressed(-2);
                        this.keyReleased(-2);
                     }

                     if (var1 < var4) {
                        this.keyPressed(-1);
                        this.keyReleased(-1);
                     }
                  } else {
                     this.keyPressed(-5);
                     this.keyReleased(-5);
                  }
                  break;
               case 4:
                  this.keyPressed(var8);
                  this.keyReleased(var8);
                  var10 = false;
                  break;
               case 5:
                  this.field_258 = MessageScreens.field_68;
                  this.field_259 = true;
                  break;
               case 6:
                  this.field_258 = MessageScreens.field_68;
                  this.field_260 = true;
            }

            if (!var10) {
               this.field_249 = false;
            }

            var1 = 1;
         }
      }

      return var1 != 0;
   }

   public final void pointerPressed(int var1, int var2) {
      this.field_255 = System.currentTimeMillis();
      this.field_250 = var1;
      this.field_251 = var2;
      this.field_252 = var1;
      this.field_253 = var2;
      this.field_249 = true;
      this.field_254 = true;
      this.method_64();
      this.field_248 = MenuUi.field_46;
   }

   public final void pointerReleased(int var1, int var2) {
      int var3;
      if (field_173[3] == 10 && (var3 = method_61(var1, var2, this.field_252, this.field_253)) != 9999) {
         this.keyPressed(var3);
         this.keyReleased(var3);
         this.field_252 = var1;
         this.field_253 = var2;
      }

      this.field_249 = false;
      this.field_254 = false;
      this.field_259 = false;
      this.field_260 = false;
   }

   public final void pointerDragged(int var1, int var2) {
      this.field_255 = System.currentTimeMillis();
      if (MessageScreens.field_65 != null) {
         if (this.field_260) {
            MessageScreens.field_68 = this.field_258 + (this.field_253 - var2 << 8);
         }

         if (this.field_259) {
            int var3 = MessageScreens.field_65[MessageScreens.field_66];
            int var4 = MessageScreens.field_74 - MessageScreens.field_75;
            MessageScreens.field_68 = this.field_258 - (this.field_253 - var2) * var3 / var4;
         }

         if (MessageScreens.field_68 < 0) {
            MessageScreens.field_68 = 0;
         }

         if (MessageScreens.field_68 > MessageScreens.field_65[MessageScreens.field_66]) {
            MessageScreens.field_68 = MessageScreens.field_65[MessageScreens.field_66];
         }
      }

      if (this.field_260 && MenuUi.field_47) {
         MenuUi.field_46 = this.field_248 - (this.field_253 - var2);
      }

      if (field_173[3] == 10) {
         int var6 = var1 - this.field_252;
         int var10 = var2 - this.field_253;
         if (var6 * var6 + var10 * var10 > 4) {
            var6 = method_61(var1, var2, this.field_250, this.field_251);
            GameScreen var8 = this;

            for (int var5 = 2; var5 > 0; var5--) {
               var8.field_261[var5] = var8.field_261[var5 - 1];
            }

            var8.field_261[0] = var6;
            boolean var9 = true;

            for (int var12 = 0; var12 < 2; var12++) {
               if (this.field_261[var12] != this.field_261[var12 + 1]) {
                  var9 = false;
               }
            }

            if (var9 && this.field_273 != this.field_261[0]) {
               this.keyPressed(this.field_261[0]);
               this.keyReleased(this.field_261[0]);
            }
         }

         this.field_250 = var1;
         this.field_251 = var2;
      }

      if (this.field_252 - var1 > 10 || this.field_252 - var1 < -10 || this.field_253 - var2 > 10 || this.field_253 - var2 < -10) {
         this.field_249 = false;
      }
   }

   // $VF: renamed from: i () void
   private void method_65() {
      boolean var1 = false;
      field_164[27] = -1;
      char[] var2 = new char[7];

      for (int var3 = 6; var3 >= 0; var3--) {
         var2[var3] = ' ';
      }

      for (int var4 = 0; var4 < 4; var4++) {
         method_59(0, field_164, 1 + (var4 << 2), 4);
      }

      if (this.field_136) {
         method_59(this.field_134, field_164, 1, 4);
      }

      field_164[25] = 0;
      field_164[26] = 0;
      this.method_92(var2, 0, field_164, 28);
   }

   // $VF: renamed from: run () void
   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // $VF: Added assignment to ensure synchronized validity
   // $VF: Could not create synchronized statement, marking monitor enters and exits
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public final void run() {
      this.repaint();
      this.serviceRepaints();
      GameScreen var3 = this;
      this.field_129 = new SoundPlayer();
      BitmapFont.field_85.method_38(1);
      this.repaint();
      this.serviceRepaints();
      BitmapFont.field_85.method_38(2);
      this.repaint();
      this.serviceRepaints();
      BitmapFont.field_85.method_38(3);
      this.repaint();
      this.serviceRepaints();
      BitmapFont.field_85.method_38(4);
      field_173[92] = BitmapFont.field_85.method_33((byte)1);
      this.field_177 = Integer.parseInt(this.method_144("NAMCO-DDSS"));
      this.field_178 = 1 << this.field_177;
      this.field_263 = Integer.parseInt(this.method_144("NAMCO-PRECISION-LACK-LOOP-COUNT"));
      this.field_135 = this.method_144("NAMCO-UPSCORE-HTML");
      if (this.field_135 != null) {
         char var1 = this.field_135.charAt(0);
         this.field_136 = var1 != 'n' && var1 != 'N';
      }

      this.field_134 = Integer.parseInt(this.method_144("NAMCO-HISCORE")) / 10;

      for (int var83 = 4; var83 >= 0; var83--) {
         var3.field_245[var83] = new byte[98];
      }

      var3.field_171 = var3.field_169[7];
      var3.field_172 = var3.field_169[8];
      var3.field_176 = System.currentTimeMillis();
      var3.field_211 = new Random();
      var3.field_181 = var3.field_182 = "";
      var3.field_147 = new Command("OK", 4, 2);
      this.repaint();
      this.serviceRepaints();
      field_173[60] = 640 - field_173[92];
      field_173[59] = 360;
      String var4 = Pacman.field_0.field_179.getAppProperty("High-Scores");
      if ("true".equals(var4)) {
         this.field_247 = true;
      } else {
         this.field_247 = false;
      }

      this.repaint();
      this.serviceRepaints();
      var3 = this;
      int var84 = 0;

      try {
         PersistentStore var2;
         var84 = (var2 = PersistentStore.open("vs_pacman", true)).getNumRecords();
         var2.close();
      } catch (Exception var76) {
      }

      label1525:
      if (var84 == 0) {
         field_173[40] = field_164[0] = 2;
         this.method_65();
         DataOutputStream var85 = null;
         boolean var58 = false /* VF: Semaphore variable */;

         label1522: {
            try {
               var58 = true;
               ByteArrayOutputStream var118 = new ByteArrayOutputStream();
               (var85 = new DataOutputStream(var118)).write(field_164, 0, 42);
               var85.writeUTF(var3.field_285);
               byte[] var119 = var118.toByteArray();
               PersistentStore var146;
               (var146 = PersistentStore.open("vs_pacman", true)).addRecord(var119, 0, var119.length);
               var146.close();
               var58 = false;
               break label1522;
            } catch (Exception var81) {
               var58 = false;
            } finally {
               if (var58) {
                  try {
                     if (var85 != null) {
                        var85.close();
                     }
                  } catch (Exception var60) {
                  }
               }
            }

            try {
               if (var85 != null) {
                  var85.close();
               }
            } catch (Exception var74) {
            }
            break label1525;
         }

         try {
            var85.close();
         } catch (Exception var75) {
         }
      } else {
         DataInputStream var86 = null;

         try {
            PersistentStore var120 = PersistentStore.open("vs_pacman", true);
            var4 = null;
            byte[] var148 = var120.getRecord(1);
            var120.close();
            if (var148 != null) {
               ByteArrayInputStream var121 = new ByteArrayInputStream(var148);
               (var86 = new DataInputStream(var121)).read(field_164, 0, 42);
               var3.field_285 = var86.readUTF();
            }
         } catch (Exception var72) {
         } finally {
            try {
               if (var86 != null) {
                  var86.close();
               }
            } catch (Exception var59) {
            }
         }

         field_173[40] = field_164[0];
      }

      this.repaint();
      this.serviceRepaints();
      var3 = this;

      try {
         if ((var84 = var3.field_194.length) != 0) {
            var3.field_200 = new Img[var84];
            var3.field_200[0] = Img.createImage("/" + var3.field_194[0]);
         }
      } catch (Exception var71) {
      }

      this.repaint();
      this.serviceRepaints();
      var84 = field_173[3];
      field_173[3] = 2;
      this.method_115();
      field_173[8] = field_173[7] = field_173[9] = 0;
      int var122 = (int)System.currentTimeMillis();

      while (this.field_132 && this.method_55((int)System.currentTimeMillis() - var122)) {
         this.repaint();
         this.serviceRepaints();
         Thread.yield();

         try {
            Thread.sleep(10L);
         } catch (Exception var70) {
         }

         field_173[8] = field_173[7];
         field_173[7] = this.method_115() | this.field_274;
         field_173[9] = (field_173[7] ^ field_173[8]) & field_173[7];
         if ((field_173[9] & 256) != 0 || field_119) {
            field_119 = false;
            if (this.field_202 == 0 && this.field_199[this.field_201] && this.field_203 > 15) {
               this.field_205 = this.field_205 + this.field_196[this.field_201] + (2 * this.field_195[this.field_201] - this.field_204);
            }

            if (this.field_202 == 1) {
               this.field_205 = this.field_205 + this.field_196[this.field_201] - this.field_204 + this.field_195[this.field_201];
            }

            if (this.field_202 == 2) {
               this.field_205 = this.field_205 + (this.field_195[this.field_201] - this.field_204);
            }
         }
      }

      field_173[3] = var84;
      field_173[8] = field_173[7] = field_173[9] = 0;
      this.field_249 = false;
      this.field_274 = 0;
      field_173[42] = 0;
      field_173[3] = 50;

      try {
         String[] var5 = new String[]{"/s1.png", "/s3.png", "/s2.png"};

         for (int var6 = 0; var6 < var5.length; var6++) {
            String var89;
            this.field_206[var6] = Img.createImage(var89 = var5[var6]);
            field_173[42] = field_173[42] + 10;
            this.field_207 = var6;
            this.repaint();
            this.serviceRepaints();
         }

         this.field_206[3] = this.field_206[1];
      } catch (Exception var80) {
      }

      long var152 = System.currentTimeMillis();
      field_173[8] = field_173[7] = field_173[9] = 0;
      this.repaint();
      this.serviceRepaints();
      if (this.field_132) {
         try {
            if (this.field_132) {
               try {
                  this.field_125 = Localization.method_54(this.field_125, 640);
                  String var90 = "/menu_buttons.png";
                  MenuUi.field_37 = Img.createImage("/menu_buttons.png");
                  String var91 = "/menu_header.png";
                  MenuUi.field_38 = Img.createImage("/menu_header.png");
                  this.repaint();
                  this.serviceRepaints();
                  String var92 = "/menu_left_border.png";
                  MenuUi.field_39 = Img.createImage("/menu_left_border.png");
                  String var93 = "/menu_right_border.png";
                  MenuUi.field_40 = Img.createImage("/menu_right_border.png");
                  this.repaint();
                  this.serviceRepaints();
                  String var94 = "/menu_softkeys.png";
                  MenuUi.field_41 = Img.createImage("/menu_softkeys.png");
                  String var95 = "/menu_top.png";
                  MenuUi.field_42 = Img.createImage("/menu_top.png");
                  this.repaint();
                  this.serviceRepaints();
                  String var96 = "/scroll_arrows.png";
                  MenuUi.field_43 = Img.createImage("/scroll_arrows.png");
                  String var97 = "/tilte_screen.png";
                  MenuUi.field_44 = Img.createImage("/tilte_screen.png");
                  this.repaint();
                  this.serviceRepaints();
                  String var98 = "/NAMCO_logo.png";
                  MenuUi.field_45 = Img.createImage("/NAMCO_logo.png");
               } catch (Exception var69) {
               }

               field_173[42] = field_173[42] + 10;
               this.repaint();
               this.serviceRepaints();
            }

            if (this.field_132) {
               String var99 = "/mp.png";
               this.field_188 = Img.createImage("/mp.png");
               field_173[42] = field_173[42] + 10;
               this.repaint();
               this.serviceRepaints();
            }

            if (this.field_132) {
               String var100 = "/cb.png";
               this.field_190 = Img.createImage("/cb.png");
               field_173[42] = field_173[42] + 5;
               this.repaint();
               this.serviceRepaints();
            }

            if (this.field_132) {
               InputStream var135 = null;

               try {
                  byte[] var123 = new byte[field_149.length << 1];
                  (var135 = this.getClass().getResourceAsStream("/g.dat")).read(field_148);
                  var135.read(var123);

                  try {
                     if (var135 != null) {
                        var135.close();
                     }
                  } catch (IOException var68) {
                  }

                  for (int var101 = 0; var101 < field_149.length; var101++) {
                     field_149[var101] = (short)method_58(var123, var101 << 1, 2);
                  }

                  for (int var102 = 0; var102 < field_148.length; var102++) {
                     field_148[var102] = (byte)(field_148[var102] << 1);
                  }

                  for (int var103 = 0; var103 < field_149.length; var103++) {
                     field_149[var103] = (short)(field_149[var103] << 1);
                  }

                  for (int var104 = 0; var104 < field_159.length; var104++) {
                     field_159[var104] = (short)(field_159[var104] << 1);
                  }

                  for (int var105 = 0; var105 < field_162.length; var105++) {
                     field_162[var105] = (short)(field_162[var105] << 1);
                  }

                  String var106 = "/g.png";
                  this.field_187 = Img.createImage("/g.png");
                  this.field_244 = 10;
               } catch (Exception var78) {
               }
            }

            field_173[42] = field_173[42] + 10;
            this.repaint();
            this.serviceRepaints();
            if (this.field_132) {
               byte[] var137 = new byte[268];
               InputStream var124;
               (var124 = this.getClass().getResourceAsStream("/data.dat")).read(var137);
               var124.close();
               int var149 = 0;

               for (int var107 = 0; var107 < 11; var107++) {
                  for (int var125 = 0; var125 < 4; var125++) {
                     this.field_169[var107][var125] = var137[var149++];
                  }
               }

               for (int var108 = 0; var108 < 7; var108++) {
                  for (int var126 = 0; var126 < 8; var126++) {
                     this.field_170[var108][var126] = var137[var149++];
                  }
               }

               for (int var109 = 0; var109 < 8; var109++) {
                  for (int var127 = 0; var127 < 21; var127++) {
                     this.field_167[var127][var109] = var137[var149++];
                  }
               }

               System.arraycopy(this.field_167, 1, this.field_168, 1, 20);
               this.field_168[0] = new byte[8];
               System.arraycopy(this.field_167[0], 0, this.field_168[0], 0, 8);
               this.field_168[0][6] = 1;
               field_173[42] = field_173[42] + 10;
               this.repaint();
               this.serviceRepaints();
               int[] var110 = new int[]{1, 0, 0, 2, 4, 2, 3, 1};
               int[] var128 = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
               int[] var138 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
               String[] var150 = new String[8];
               this.repaint();
               this.serviceRepaints();

               for (int var7 = 0; var7 < 8; var7++) {
                  var150[var7] = "snd/p" + var7 + ".mid";
               }

               this.repaint();
               this.serviceRepaints();
               this.field_129.configure(var150, var138, var110, var128);
               this.repaint();
               this.serviceRepaints();
               int var153 = 1;

               while (this.field_132 && var153 > 0) {
                  try {
                     var153 = this.field_129.loadNext();
                  } catch (Exception var67) {
                  }

                  if (field_173[42] < 100) {
                     field_173[42] = field_173[42] + 3;
                  }

                  this.repaint();
                  this.serviceRepaints();
               }

               this.repaint();
               this.serviceRepaints();
            }
         } catch (Exception var79) {
         }

         if (this.field_132) {
            field_173[42] = 100;

            while (System.currentTimeMillis() - var152 < 2000L) {
               this.repaint();
               this.serviceRepaints();

               try {
                  Thread.sleep(10L);
               } catch (Exception var66) {
               }

               if (field_119) {
                  field_119 = false;
                  break;
               }
            }

            NicknameEditor.method_12(24);
            NicknameEditor.field_54[6] = true;
            this.method_120();
            String var139;
            String var140;
            if ((var139 = this.method_144("NAMCO-RUNTIME-LOCALIZE")) != null && var139.charAt(0) == 'Y') {
               var140 = System.getProperty("microedition.locale");
            } else {
               var140 = null;
            }

            if (var140 != null) {
               var140 = var140.substring(0, 2);
            }

            if (var140 == null || this.method_144("NAMCO-AVAILABLE-LANGUAGES").indexOf(var140) == -1) {
               var140 = this.method_144("NAMCO-DEFAULT-LANGUAGE");
            }

            if (var140 != null) {
               this.field_123 = new Localization(var140, false, this.field_247);
            } else if (field_164[27] < this.field_279.length && field_164[27] != -1) {
               this.field_123 = new Localization(this.field_279[field_164[27]], false, this.field_247);
            } else {
               field_173[3] = 0;
               this.method_121();
            }

            this.method_135();
            if (this.field_247) {
               int var141 = MenuUi.field_29;
               MenuUi.field_29 = 1;
               MenuUi.method_16(60);
               boolean var129 = false;
               MenuUi.field_29 = var141;
               MenuUi.field_28[1] = new boolean[9];
               int var142 = MenuUi.field_29;
               MenuUi.field_29 = 1;
               int var151;
               if ((var151 = MenuUi.method_16(128)) == -1) {
                  var151 = MenuUi.method_16(60);
               }

               if (field_164[26] == 1) {
                  MenuUi.field_27[1][var151] = Localization.field_115[128];
               } else {
                  MenuUi.field_27[1][var151] = Localization.field_115[60];
               }

               MenuUi.field_29 = var142;
               MenuUi.method_14();
            }

            if (this.field_132) {
               var84 = field_173[59];
               var122 = field_173[60];
               MenuUi.method_15(this.field_210, var84 - 2, var122);
               Localization.field_116[4] = Localization.method_54(Localization.field_116[4], var84 - 44);
               Localization.field_116[5] = Localization.method_54(Localization.field_116[5], var84 - 44);
               if (this.field_123 != null && Localization.field_116 != null) {
                  this.field_125 = Localization.field_116[6];
               }

               this.field_125 = Localization.method_54(this.field_125, 640);
               this.field_181 = Localization.field_115[76];
               this.field_182 = this.method_144("NAMCO-AUTHON");
               if (this.field_182 != null && this.field_182.charAt(0) == 'Y') {
                  this.field_182 = Localization.field_115[53];
               } else {
                  this.field_182 = "";
               }
            }

            MenuUi.field_29 = 14;
            MenuUi.field_30 = 0;
            field_173[3] = 51;
            MenuUi.field_49 = 3;
            Thread.yield();
            System.currentTimeMillis();
            boolean var144 = false;
            this.field_272 = 9999;
            this.field_249 = false;
            this.method_115();

            while (this.field_132) {
               this.repaint();
               this.serviceRepaints();

               try {
                  Thread.sleep(10L);
               } catch (Exception var64) {
               }

               this.method_63();
               Thread.yield();
               field_173[8] = field_173[7];
               field_173[7] = this.method_115() | this.field_274;
               field_173[9] = (field_173[7] ^ field_173[8]) & field_173[7];
               var122 = field_173[9];
               if (this.field_143 == this.field_146) {
                  var122 = 256;
               }

               if (MenuUi.method_13(var122)) {
                  this.field_143 = null;
                  if (MenuUi.field_30 == 0) {
                     field_173[40] = 2;
                     MenuUi.field_27[15][0] = Localization.field_115[104];
                     this.field_129.enableSound();
                     field_164[0] = (byte)field_173[40];
                  } else {
                     field_173[40] = 1;
                     MenuUi.field_27[15][0] = Localization.field_115[105];
                     this.field_129.disableSound();
                     field_164[0] = (byte)field_173[40];
                  }
                  break;
               }
            }

            this.field_272 = 9999;
            this.field_249 = false;
            field_173[8] = field_173[7] = field_173[9] = 0;
            field_120 = true;
            field_173[6] = 0;
            this.method_69();
            if (this.field_206 != null) {
               for (int var132 = 0; var132 < this.field_206.length; var132++) {
                  this.field_206[var132] = null;
               }
            }

            this.field_206 = null;
            System.gc();
         }
      }

      while (this.field_132) {
         {
            if (!this.field_265 || this.field_246) {
               this.method_142();
            }

            if (this.field_265) {
               if (this.field_262) {
                  field_173[0] = 2;
                  MenuUi.field_49 = 1;
                  this.field_262 = false;
               }

               this.method_85();
               this.method_115();
               if (this.field_282 != field_173[3]) {
                  switch (field_173[3]) {
                     case 0:
                     case 4:
                     case 6:
                     case 11:
                     case 14:
                     case 16:
                     case 18:
                     case 22:
                     case 23:
                     case 24:
                     case 27:
                     case 28:
                     case 30:
                     case 38:
                     case 51:
                     case 52:
                     case 53:
                     case 54:
                     case 55:
                     case 56:
                        MenuUi.field_46 = 0;
                        this.field_248 = 0;
                        MenuUi.field_49 = 2;
                     case 1:
                     case 2:
                     case 3:
                     case 8:
                     case 9:
                     case 10:
                     case 13:
                     case 15:
                     case 19:
                     case 20:
                     case 21:
                     case 33:
                     case 35:
                     case 36:
                     case 37:
                     case 39:
                     case 40:
                     case 41:
                     case 42:
                     case 43:
                     case 44:
                     case 45:
                     case 46:
                     case 47:
                     case 48:
                     case 49:
                     case 50:
                     case 58:
                     default:
                        break;
                     case 5:
                     case 7:
                     case 17:
                     case 25:
                     case 26:
                     case 29:
                     case 31:
                     case 32:
                     case 57:
                     case 60:
                        this.field_258 = 0;
                        MessageScreens.field_68 = 0;
                        MenuUi.field_49 = 2;
                        break;
                     case 12:
                     case 34:
                        MenuUi.field_46 = 0;
                        this.field_248 = 0;
                        MenuUi.field_49 = 2;
                        break;
                     case 59:
                        MenuUi.field_46 = 0;
                        this.field_248 = 0;
                        MenuUi.field_49 = 2;
                  }
               }

               this.field_282 = field_173[3];
               this.repaint();
               this.serviceRepaints();

               do {
                  try {
                     Thread.sleep(10L);
                  } catch (InterruptedException var63) {
                  }
               } while (this.field_223 == 0 && System.currentTimeMillis() - this.field_176 < (long)field_173[86]);

               boolean var114 = false;
               if (this.field_225 == Integer.MAX_VALUE) {
                  field_173[98] = 0;
                  field_173[49] = 0;
                  if (this.field_175 < 3000) {
                     switch (field_173[3]) {
                        case 10:
                           if (field_173[13] == 0) {
                              for (int var116 = 0; var116 < 4; var116++) {
                                 int[] var145;
                                 if ((var145 = this.field_174[var116])[12] > 0) {
                                    var145[12] -= this.field_175;
                                 }

                                 if (var145[12] < 0) {
                                    var145[12] = 0;
                                 }
                              }

                              if (field_173[24] > 0) {
                                 field_173[24] = field_173[24] - this.field_175;
                              }

                              if (field_173[24] < 0) {
                                 field_173[24] = 0;
                              }

                              if (field_173[12] < 3) {
                                 field_173[29] = field_173[29] + this.field_175;
                              }

                              if (field_173[31] < 7) {
                                 field_173[32] = field_173[32] + this.field_175;
                              }

                              if (field_173[1] == 24 && field_173[97] > 0) {
                                 field_173[97] = field_173[97] - this.field_175;
                              }

                              for (int var117 = 3; var117 >= 0; var117--) {
                                 this.field_174[var117][15] = this.field_174[var117][15] - this.field_175;
                                 if (this.field_174[var117][15] < 0) {
                                    this.field_174[var117][15] = 0;
                                 }
                              }
                           }

                           if (field_173[85] > 0) {
                              field_173[85] = field_173[85] - this.field_175;
                           }

                           if (field_173[85] < 0) {
                              field_173[85] = 0;
                           }
                        case 11:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 27:
                        case 28:
                        case 30:
                        case 31:
                        case 32:
                        default:
                           break;
                        case 12:
                        case 13:
                        case 25:
                        case 26:
                        case 29:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                           if (field_173[85] > 0) {
                              field_173[85] = field_173[85] - this.field_175;
                           }

                           if (field_173[85] < 0) {
                              field_173[85] = 0;
                           }
                     }
                  }

                  if (this.field_223 == 0 || this.field_236 == null) {
                     this.field_175 = (int)(System.currentTimeMillis() - this.field_176);
                  }

                  this.field_176 = System.currentTimeMillis();
                  field_173[6]++;
               } else if (this.field_223 == 0) {
                  field_173[98] = field_173[98] + 1500;
                  if (field_173[98] > 10000) {
                     field_173[98] = 0;
                     if (++field_173[49] >= 2) {
                        this.method_71();
                     } else {
                        this.method_125();

                     }
                  }
               }
            }
         }
      }

      this.field_179.notifyDestroyed();
   }

   // $VF: renamed from: a (int) void
   private void method_66(int var1) {
      this.field_220 = field_173[23] = field_173[37] = field_173[11] = field_173[52] = field_173[34] = field_173[25] = field_173[4] = field_173[6] = field_173[75] = field_173[35] = field_173[1] = 0;
      this.field_212 = this.field_166[0];
      int var2 = var1;
      GameScreen var8 = this;
      InputStream var3 = null;

      try {
         if (var2 != var8.field_243) {
            if (field_155[10] == null) {
               field_155[0] = new short[1];
               field_155[1] = new short[2];
               field_155[2] = new short[2];
               field_155[3] = new short[2];
               field_155[4] = new short[2];
               field_155[5] = new short[2];
               field_155[6] = new short[4];
               field_155[7] = new short[4];
               field_155[8] = new short[4];
               field_155[9] = new short[4];
               field_155[10] = new short[4];
            }

            field_150 = (var3 = var8.getClass().getResourceAsStream("/m" + var2 + ".dat")).read();
            field_151 = var3.read();
            field_154 = var3.read() << 8 | var3.read();

            for (int var4 = 0; var4 <= 10; var4++) {
               for (int var5 = 0; var5 < field_155[var4].length; var5++) {
                  field_155[var4][var5] = (short)var3.read();
                  if (var4 == 8 && (field_155[var4][var5] & 128) != 0) {
                     field_155[var4][var5] = (short)(field_155[var4][var5] | -256);
                  }
               }
            }

            int var12 = (field_150 + 2 >> 1) * field_151;
            if (field_165 == null || field_165.length < var12) {
               field_165 = new byte[var12];
               var8.field_180 = new int[var12 << 1];
            }

            var3.read(field_165, 0, var12);

            try {
               if (var3 != null) {
                  var3.close();
               }
            } catch (IOException var6) {
            }
         }

         field_152 = field_150 * 10;
         field_153 = field_151 * 10;
         if (var2 != var8.field_243 || 10 != var8.field_244) {
            var8.field_189 = Img.createImage("/m" + var2 + ".png");
            field_161[2] = field_161[4] = field_161[6] = (short)field_152;
            field_161[3] = field_161[7] = (short)field_153;
         }

         var8.field_243 = var2;
         if (var8.field_191 == null || var8.field_191.getWidth() != field_152 || var8.field_191.getHeight() != field_153) {
            var8.field_191 = Img.createImage(field_152, field_153);
            var8.field_183 = var8.field_191.getGraphics();
            field_173[70] = field_173[59] - field_152 >> 1;
         }

         var8.field_185 = Img.createImage(field_152, field_153);
         var8.field_186 = var8.field_185.getGraphics();
      } catch (Exception var7) {
      }

      this.method_95();
      method_75();
      this.method_73(0);
      this.method_72();
      field_173[28] = 3;
      field_173[21] = 0;
      field_173[3] = 10;
      field_173[0] = 2;

      for (int var9 = this.field_219 - 1; var9 >= 0; var9--) {
         this.method_56(this.field_245[var9]);
      }

      var1 = field_153 + field_162[71] + 3 + -4 + (this.field_219 == 1 ? 16 : 80);
      field_173[71] = (field_173[60] - var1 >> 1) + 8;
      if (this.field_219 == 1) {
         field_173[71] = field_173[71] + 16;
      }
   }

   // $VF: renamed from: j () void
   private void method_67() {
      if (field_173[13] == 0) {
         switch (field_173[1]) {
            case 0:
               field_173[73] = 1;
               field_173[72] = 1;
               this.field_129.play(0);
               field_173[38] = 0;
               field_173[15] = 1;
               field_173[85] = 2300;
               field_173[1] = 23;
               return;
            case 1:
               field_173[73] = 0;
               this.method_73(1);
               method_75();
               field_173[27] = 1;
               field_173[72] = 1;
               if (field_173[38] == 2) {
                  field_173[12] = 0;
                  field_173[0] = 2;
               } else {
                  field_173[4] = 0;
                  field_173[14] = 0;
                  field_173[23] = 0;
                  field_173[0] = 16;
                  GameScreen var10 = this;
                  field_173[12] = field_173[25] + 1;
                  if (field_173[12] > 3) {
                     field_173[12] = 3;
                  }

                  for (int var12 = 1; var12 <= field_173[12]; var12++) {
                     var10.field_174[var12][11] = 1;
                  }

                  if (field_173[38] == 0) {
                     field_173[28]--;
                     field_173[0] = 16;
                  } else {
                     this.method_72();
                     field_173[0] = 2;
                     field_173[25]++;
                     field_173[34] = field_173[25];
                     if (field_173[34] > 20) {
                        field_173[34] = 20;
                     }

                     this.field_212 = this.field_166[field_173[34]];
                     this.field_129.stopAndForgetLooping();
                  }
               }

               this.field_208 = 0;

               for (int var11 = 3; var11 >= 0; var11--) {
                  this.field_213[var11] = this.field_213[var11 + 5] = var11 == 2 ? 0 : 2;
               }

               this.field_213[4] = this.field_213[9] = 2;
               field_173[5] = 0;
               field_173[13] = 0;
               field_173[85] = 2500;
               field_173[15] = 2;
               field_173[1] = 23;
               field_173[3] = 10;
               return;
            case 2:
               field_173[72] = 0;
               field_173[27] = 2;
               this.field_174[0][8] = 1;
               boolean var9 = false;
               this.field_174[0][14] = 0;
               this.field_174[1][8] = 7;
               this.field_174[2][8] = 5;
               this.field_174[3][8] = 5;
               if (this.field_219 > 1 && field_173[4] > field_154 - 16) {
                  field_173[97] = 1800;
               }

               field_173[1] = 24;
               field_173[31] = 0;
               field_173[32] = 0;
               field_173[62] = 0;
               field_173[29] = 0;
               return;
            case 3:
               field_173[27] = 1;
               this.field_174[0][8] = 0;
               this.field_174[1][8] = 0;
               this.field_174[2][8] = 0;
               this.field_174[3][8] = 0;
               field_173[85] = 2000;
               field_173[15] = 4;
               field_173[1] = 23;
               return;
            case 4:
               field_173[21] = 0;
               this.method_73(0);
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
               field_173[0] = 4 + (field_173[1] & 1);
               field_173[85] = 500;
               field_173[15] = field_173[1] + 1;
               field_173[1] = 23;
               return;
            case 12:
               field_173[76] = 0;
               switch (field_173[25]) {
                  case 8:
                  case 12:
                  case 16:
                     field_173[76]++;
                  case 4:
                     field_173[76]++;
                  case 1:
                     field_173[76]++;
                     field_173[38] = 1;
                     field_173[1] = 27;
                     return;
                  default:
                     field_173[38] = 1;
                     field_173[1] = 1;
                     field_173[0] = 2;
                     return;
               }
            case 13:
               field_173[85] = 1000;
               field_173[15] = 14;
               field_173[1] = 23;
               return;
            case 14:
               field_173[21] = 9;
               boolean var8 = false;
               this.field_174[0][7] = 0;
               this.field_174[1][7] = 0;
               this.field_174[2][7] = 0;
               this.field_174[3][7] = 0;
               field_173[24] = 0;
               field_173[85] = 100;
               field_173[15] = 15;
               field_173[1] = 23;
               field_173[0] = 2;
               this.field_129.play(4);
               return;
            case 15:
            case 16:
            case 17:
            case 18:
               field_173[21]++;
               field_173[85] = 100;
               if (field_173[21] >= 19) {
                  field_173[85] = field_173[85] + 100;
                  field_173[15] = 19;
               } else {
                  field_173[15] = field_173[1];
               }

               field_173[1] = 23;
               return;
            case 19:
               field_173[27] = 0;
               field_173[28]--;
               if (field_173[28] < 0) {
                  field_173[27] = 0;
                  this.method_73(0);
                  field_173[1] = 20;
                  field_173[28] = 0;
                  return;
               }

               field_173[38] = 2;
               field_173[1] = 1;
               this.method_57();
               if (this.field_219 > 1) {
                  method_68();
                  return;
               }
               break;
            case 20:
               field_173[27] = 0;
               field_173[0] = 9;
               field_173[75] = 1;
               int var7 = field_173[11];
               if (this.field_219 <= 1 && this.field_242 == 0 && var7 > 0) {
                  String scoreName = new String(NicknameEditor.field_60).trim();
                  LocalScores.submit(scoreName.isEmpty() ? "PAC" : scoreName, var7 * 10);
               }

               if (field_173[47] < var7 && this.field_219 <= 1) {
                  field_173[47] = var7;
                  if (this.field_136 && var7 >= this.field_134 && this.field_242 == 0) {
                     field_164[25] = 1;
                     field_164[26] = 0;
                     if (this.field_247) {
                        int var13 = MenuUi.field_29;
                        MenuUi.field_29 = 1;
                        int var14;
                        if ((var14 = MenuUi.method_16(128)) == -1) {
                           var14 = MenuUi.method_16(60);
                        }

                        MenuUi.field_27[1][var14] = Localization.field_115[60];
                        MenuUi.field_29 = var13;
                     }

                     MenuUi.method_14();
                     method_59(field_173[25], field_164, 17, 2);
                  }

                  method_59(var7, field_164, 1 + (this.field_242 << 2), 4);
                  this.method_60();
               }

               field_173[85] = 2000;
               field_173[15] = 26;
               field_173[1] = 23;
               return;
            case 21:
            case 24:
            case 25:
            case 28:
            default:
               break;
            case 22:
               field_173[2]--;
               if (field_173[2] == 0) {
                  field_173[1] = field_173[15];
                  return;
               }
               break;
            case 23:
               if (field_173[85] == 0) {
                  field_173[1] = field_173[15];
                  return;
               }
               break;
            case 26:
               this.method_57();
               if (field_173[1] == 26) {
                  if (this.field_219 <= 1) {
                     this.method_69();
                     return;
                  }

                  field_173[10] = 0;
                  int[] var3 = new int[this.field_219];
                  int var5 = 0;

                  for (int var1 = this.field_219 - 1; var1 >= 0; var1--) {
                     var3[var1] = method_58(this.field_245[var1], 6, 3);
                  }

                  for (int var6 = this.field_219 - 1; var6 >= 0; var6--) {
                     field_173[10] = field_173[10] << 3;
                     int var4 = Integer.MAX_VALUE;

                     for (int var2 = this.field_219 - 1; var2 >= 0; var2--) {
                        if (var3[var2] < var4) {
                           var5 = var2;
                           var4 = var3[var2];
                        }
                     }

                     field_173[10] = field_173[10] | var5;
                     var3[var5] = Integer.MAX_VALUE;
                  }

                  field_173[3] = 37;
                  this.field_129.play(15);
                  this.field_208 = 0;
                  this.field_275 = true;
                  return;
               }

               if (this.field_219 > 1) {
                  method_68();
                  return;
               }
               break;
            case 27:
               field_173[46] = field_173[86];
               field_173[86] = Integer.parseInt(this.method_144("NAMCO-CBWT"));
               this.field_129.play(7);
               field_173[85] = field_163[field_173[76] - 1];
               field_173[1] = 29;
               field_173[77] = 0;
               field_173[3] = 13;
               field_173[0] = 2;
               return;
            case 29:
               if (field_173[85] == 0) {
                  field_173[1] = 1;
                  field_173[86] = field_173[46];
               }
         }
      }
   }

   // $VF: renamed from: k () void
   private static void method_68() {
      field_173[85] = 4000;
      field_173[3] = 35;
   }

   // $VF: renamed from: l () void
   private void method_69() {
      this.field_219 = 1;
      this.field_209 = -1;
      this.field_275 = false;
      this.field_225 = Integer.MAX_VALUE;
      field_173[87] = Integer.parseInt(this.method_144("NAMCO-LOOP"));
      field_173[86] = Integer.parseInt(this.method_144("NAMCO-WAIT"));
      MessageScreens.field_69 = 2048;
      field_173[1] = 0;
      field_173[3] = 3;
      this.field_129.reset();
      field_173[27] = 0;
      field_173[0] = 1;
      MenuUi.field_49 = 1;
      field_173[35] = 0;
      MenuUi.field_36 = false;
      this.field_143 = null;
      MenuUi.field_35 = true;
      MenuUi.field_32 = true;
      field_173[47] = method_58(field_164, 1, 4);
   }

   // $VF: renamed from: b (int) void
   private void method_70(int var1) {
      MessageScreens.field_66 = var1;
      this.field_157 = 7;
      this.field_158 = -1;
      this.field_156 = 24;
      field_173[3] = 32;
   }

   // $VF: renamed from: m () void
   private void method_71() {
      this.field_225 = Integer.MAX_VALUE;
      this.field_275 = false;
      this.field_129.reset();
      this.method_125();
      this.method_126();
      MessageScreens.field_66 = 10;
      field_173[3] = 31;
   }

   // $VF: renamed from: n () void
   private void method_72() {
      int var3 = (field_150 + 2) * field_151;

      for (int var1 = 0; var1 < var3; var1++) {
         int var2 = var1 >> 1;
         int var4 = field_165[var2];
         if ((var1 & 1) == 0) {
            var4 >>= 4;
         }

         if ((var4 = var4 & 15) == 5) {
            var4 = 16;
         }

         if (var4 == 6) {
            var4 = 32;
         }

         if (var4 == 7) {
            var4 = 33;
         }

         this.field_180[var1] = var4;
      }
   }

   // $VF: renamed from: c (int) void
   private void method_73(int var1) {
      for (int var2 = 0; var2 < 4; var2++) {
         int[] var3;
         (var3 = this.field_174[var2])[0] = (field_155[9][var2] << 3) + 4 + 4;
         var3[1] = (field_155[10][var2] << 3) + 4;
         var3[2] = var3[0];
         var3[3] = var3[2];
         var3[4] = var3[1];
         var3[5] = this.field_169[9][var2];
         var3[6] = 0 + (this.field_219 != 1 && var2 == this.field_220 ? 32 : var2 << 3) + (var3[5] << 1);
         var3[7] = var1;
         var3[8] = 0;
         var3[11] = 0;
         var3[10] = 0;
         var3[9] = 0;
         var3[12] = 0;
         var3[13] = 0;
         var3[14] = 1;
         var3[15] = 0;
      }

      field_173[89] = field_173[90] = 70;
   }

   // $VF: renamed from: o () void
   private void method_74() {
      for (int var1 = 0; var1 < 4; var1++) {
         boolean var5 = false;
         boolean var4 = this.field_215[var1];
         int[] var3 = this.field_174[var1];
         if (field_173[13] == 0 || var3[7] == 3) {
            if (var3[8] == 11) {
               var3[8] = 4;
               var3[7] = 3;
            } else {
               int var6 = this.method_79(var3[0], var3[1]);
               if (var3[7] == 3) {
                  var3[13] += 32;
               } else {
                  int var7;
                  if ((var6 & 16) != 0) {
                     var7 = 6;
                  } else if (var1 == 0 && field_173[14] != 0 && var3[7] != 2) {
                     var7 = 1 + field_173[14];
                  } else if (var3[15] > 0 && var3[7] != 2) {
                     var7 = 3;
                  } else {
                     var7 = 3 + var3[7];
                  }

                  int var2 = this.field_212[6];
                  if (!var4) {
                     if (++var2 > 3) {
                        var2 = 3;
                     }
                  }

                  var3[13] += this.field_170[var2][var7];
                  if (var3[15] > 0 && var3[7] == 2) {
                     var3[13] += 2;
                  }
               }

               if (var3[13] >= 32) {
                  var3[13] -= 32;
                  if (var3[9] == 1) {
                     var3[9] = 0;
                     var3[10] = 0;
                     if (var3[7] != 3) {
                        var3[12] = this.field_212[4] * 1000;
                        if (var3[8] >= 1 && var3[8] <= 3) {
                           if (var3[12] != 0) {
                              var3[8] = 3;
                           }

                           if (var3[14] == 0 && var3[7] != 3 && var4) {
                              var3[5] ^= 2;
                           }
                        }

                        if (var3[12] != 0) {
                           var3[7] = 2;
                        }
                     }
                  } else if (var3[10] == 1) {
                     var3[10] = 0;
                     if ((var3[8] == 1 || var3[8] == 2) && var3[14] == 0 && var3[7] != 3 && var4) {
                        var3[5] ^= 2;
                     }
                  }

                  if (var3[8] != 0) {
                     if (var3[14] == 1) {
                        switch (var3[8]) {
                           case 5:
                              if (var3[11] == 1 && (field_155[2][1] + 2 << 3) + 4 == var3[1]) {
                                 var3[11] = 2;
                                 var3[8] = 6;
                                 if (var1 == 2) {
                                    var3[5] = 0;
                                 }

                                 if (var1 == 3) {
                                    var3[5] = 2;
                                 }
                              } else if (var3[1] < field_155[2][1] + 2 << 3) {
                                 var3[5] = 1;
                              } else if (var3[1] > field_155[2][1] + 3 << 3) {
                                 var3[5] = 3;
                              }
                              break;
                           case 6:
                              if ((field_155[2][0] << 3) + 4 + 2 == var3[0]) {
                                 var3[8] = 7;
                                 var3[5] = 3;
                              }
                              break;
                           case 7:
                              if (var3[11] != 0) {
                                 if (var3[11] < 3) {
                                    var3[5] = 3;
                                 }

                                 var3[11] = 3;
                                 if (var3[1] > (field_155[2][1] - 1 << 3) + 4) {
                                    break;
                                 }

                                 var3[1] = (field_155[2][1] - 1 << 3) + 4;
                                 var3[11] = 0;
                                 var3[14] = 0;
                                 this.method_83(var1, false);
                                 var3[0] -= 2;
                                 if (!var4 && (this.field_213[var1] & 1) == 0) {
                                    this.method_76(var1);
                                    break;
                                 }

                                 var3[5] = 2;
                              } else if (var3[1] < (field_155[2][1] + 1 << 3) + 4 + 2) {
                                 var3[5] = 1;
                              } else if (var3[1] > (field_155[2][1] + 3 << 3) + 4 - 2) {
                                 var3[5] = 3;
                              }
                              break;
                           case 8:
                              if (var3[1] == (field_155[2][1] + 2 << 3) + 4) {
                                 switch (var1) {
                                    case 0:
                                    case 1:
                                       var3[8] = 7;
                                       var3[7] = 1;
                                       var3[11] = 1;
                                       break;
                                    case 2:
                                       var3[5] = 2;
                                       var3[8] = 9;
                                       break;
                                    case 3:
                                       var3[5] = 0;
                                       var3[8] = 10;
                                 }
                              }
                              break;
                           case 9:
                              if (var3[0] == (field_155[2][0] - 2 << 3) + 4 + 2) {
                                 var3[8] = 5;
                                 var3[7] = 1;
                                 var3[5] = 0;
                                 var3[11] = 1;
                              }
                              break;
                           case 10:
                              if (var3[0] == (field_155[2][0] + 2 << 3) + 4 + 2) {
                                 var3[8] = 5;
                                 var3[7] = 1;
                                 var3[5] = 2;
                                 var3[11] = 1;
                              }
                        }
                     } else if (!var4 && var3[8] > 0 && var3[8] < 4) {
                        var5 = this.method_76(var1);
                     } else if ((var3[0] & 7) == 4 && (var3[1] & 7) == 4) {
                        label256:
                        switch (var3[8]) {
                           case 1:
                              var3[5] = this.method_82(var3[0], var3[1], field_155[7][var1] << 3, field_155[8][var1] << 3, var3[5], var6);
                              break;
                           case 2:
                              switch (var1) {
                                 case 0:
                                    var3[5] = this.method_82(var3[0], var3[1], field_173[16], field_173[17], var3[5], var6);
                                    break label256;
                                 case 1:
                                    var3[5] = this.method_82(
                                       var3[0],
                                       var3[1],
                                       field_173[16] + (this.field_171[field_173[20]] * 3 << 3),
                                       field_173[17] + (this.field_172[field_173[20]] * 3 << 3),
                                       var3[5],
                                       var6
                                    );
                                    break label256;
                                 case 2:
                                    var3[5] = this.method_82(
                                       var3[0],
                                       var3[1],
                                       (field_173[16] << 1) - this.field_174[0][0],
                                       (field_173[17] << 1) - this.field_174[0][1],
                                       var3[5],
                                       var6
                                    );
                                    break label256;
                                 case 3:
                                    if (var3[0] - field_173[16] > -64
                                       && var3[0] - field_173[16] < 64
                                       && var3[1] - field_173[17] > -64
                                       && var3[1] - field_173[17] < 64) {
                                       var3[5] = this.method_81(var1, var6 & 32);
                                    } else {
                                       var3[5] = this.method_82(var3[0], var3[1], field_173[16], field_173[17], var3[5], var6);
                                    }
                                 default:
                                    break label256;
                              }
                           case 3:
                              var3[5] = this.method_81(var1, 0);
                              break;
                           case 4:
                              if (var3[0] == (field_155[2][0] << 3) + 4 && var3[1] == (field_155[2][1] - 1 << 3) + 4) {
                                 var3[8] = 8;
                                 var3[5] = 1;
                                 var3[14] = 1;
                                 var3[0] += 2;
                              } else {
                                 var3[5] = this.method_82(var3[0], var3[1], field_155[2][0] << 3, field_155[2][1] - field_155[0][0] << 3, var3[5], var6);
                              }
                        }
                     }
                  }

                  switch (var3[7]) {
                     case 1:
                        var3[6] = 0 + (this.field_219 != 1 && var1 == this.field_220 ? 32 : var1 << 3) + (var3[5] << 1);
                        break;
                     case 2:
                        if (var3[12] < 3000 && ((int)System.currentTimeMillis() & 512) == 0 && field_173[1] == 24) {
                           var3[6] = 50;
                        } else {
                           var3[6] = 48;
                        }

                        if (var3[12] == 0) {
                           var3[7] = 1;
                           if (var3[8] == 3) {
                              this.method_83(var1, false);
                           }
                        }
                        break;
                     case 3:
                        var3[6] = 40 + (var3[5] << 1);
                  }

                  if (!var5 && field_173[1] == 24 || field_173[1] == 23 && field_173[15] == 14) {
                     var3[6] += field_173[6] & 1;
                  }

                  if (var3[8] != 0 && !var5) {
                     var3[0] += this.field_169[7][var3[5]] << this.field_177;
                     var3[1] += this.field_169[8][var3[5]] << this.field_177;
                     if (!var4 && var3[14] == 0) {
                        if ((var3[5] & 1) == 0) {
                           var3[1] -= (var3[1] & 7) - 4;
                        } else {
                           var3[0] -= (var3[0] & 7) - 4;
                        }
                     }
                  }

                  if (var3[0] - 8 >= field_150 + 2 << 3) {
                     var3[0] -= field_150 + 2 << 3;
                  }

                  if (var3[0] - 8 < 0) {
                     var3[0] += field_150 + 2 << 3;
                  }

                  if (var3[0] - 8 > field_150 << 3) {
                     var3[2] = var3[0] - (field_150 + 2 << 3);
                  } else {
                     var3[2] = var3[0];
                  }

                  if (!var4 && field_173[88] == 2 && var3[0] == (field_155[1][0] << 3) + 4 && var3[1] == (field_155[1][1] << 3) + 4 && var3[7] != 3) {
                     field_173[24] = 3000;
                     field_173[88] = 1;
                     int var8 = this.method_94(var1);
                     method_59(method_58(this.field_245[var8], 6, 3) + this.field_212[5] * 10, this.field_245[var8], 6, 3);
                     field_173[0] = 2;
                     var3[15] = 12000;
                     this.field_129.play(5);
                  }
               }
            }
         }
      }
   }

   // $VF: renamed from: p () void
   private static void method_75() {
      System.gc();
      field_173[24] = 0;
      field_173[88] = 0;
      field_173[33] = 0;
      field_173[16] = (field_155[4][0] << 3) + 4 + 4;
      field_173[17] = (field_155[4][1] << 3) + 4;
      field_173[22] = field_173[16];
      field_173[18] = field_173[22];
      field_173[19] = field_173[17];
      field_173[20] = 2;
      field_173[21] = 0;
      field_173[43] = 0;
      field_173[29] = 0;
      field_173[36] = 0;
      field_173[63] = 0;
   }

   // $VF: renamed from: b (int) boolean
   private boolean method_76(int var1) {
      boolean var2 = false;
      int[] var3 = this.field_174[var1];
      if ((var1 = this.field_213[var1]) >= 0) {
         int var4;
         if ((var4 = this.method_78(var3[0], var3[1], var1) & 15) < 3) {
            var3[5] = var1;
         }

         if (var1 != var3[5]) {
            var4 = this.method_78(var3[0], var3[1], var3[5]) & 15;
         }

         if (var4 >= 3) {
            var2 = true;
         }
      }

      return var2;
   }

   // $VF: renamed from: q () void
   private void method_77() {
      if (field_173[13] == 0) {
         for (int var1 = 0; var1 < 4; var1++) {
            int[] var2;
            int var3 = (var2 = this.field_174[var1])[0] - field_173[16];
            int var4 = var2[1] - field_173[17];
            boolean var5 = false;
            if (var3 >= 0 && var3 < 4 && var4 >= 0 && var4 < 4) {
               var5 = true;
            }

            if (var2[0] >> 3 == field_173[16] >> 3 && var2[1] >> 3 == field_173[17] >> 3) {
               var5 = true;
            }

            if (var5 && var2[14] == 0) {
               if (var2[7] == 2 || var2[7] == 1 && var2[9] == 1) {
                  field_173[85] = 1000;
                  field_173[13] = 1;
                  field_173[30]++;
                  if (!this.field_215[var1]) {
                     field_173[30]++;
                  }

                  field_173[11] = field_173[11] + (20 << field_173[30] - 1);
                  field_173[52] = 1;
                  var2[8] = 11;
                  var2[12] = 0;
                  var2[7] = 0;
                  this.field_129.play(3);
               } else if (var2[7] == 1 && field_173[43] == 0) {
                  field_173[43] = 1;
                  this.field_216 = this.method_94(var1);
                  if (this.field_216 != -1) {
                     int var6 = (field_154 - field_173[4]) * 5 / 10 + this.field_212[5] * 10;
                     method_59(method_58(this.field_245[this.field_216], 6, 3) + var6, this.field_245[this.field_216], 6, 3);
                     this.field_217 = Localization.field_115[39];
                     this.field_218 = Integer.toString(var6) + "0!";
                     field_173[0] = 2;
                  } else {
                     this.field_217 = Localization.field_115[65];
                     this.field_218 = Localization.field_115[18];
                  }

                  this.field_216 = var1;
               }
            }

            if (field_173[13] != 0) {
               break;
            }
         }

         if (field_173[43] > 0) {
            field_173[43]++;
         }

         if (field_173[43] > 1 && field_173[1] == 24) {
            field_173[1] = 13;
            field_173[27] = 3;
            this.field_174[0][8] = 0;
            this.field_174[1][8] = 0;
            this.field_174[2][8] = 0;
            this.field_174[3][8] = 0;
         }
      }
   }

   // $VF: renamed from: a (int, int, int) int
   private int method_78(int var1, int var2, int var3) {
      if ((var1 = (var1 >> 3) + this.field_171[var3]) >= field_150 + 2) {
         var1 = 0;
      }

      if (var1 < 0) {
         var1 = field_150 + 1;
      }

      return this.field_180[((var2 >> 3) + this.field_172[var3]) * (field_150 + 2) + var1];
   }

   // $VF: renamed from: a (int, int) int
   private int method_79(int var1, int var2) {
      if ((var1 = var1 >> 3) >= field_150 + 2) {
         var1 = 0;
      }

      if (var1 < 0) {
         var1 = field_150 + 1;
      }

      return this.field_180[(var2 >> 3) * (field_150 + 2) + var1];
   }

   // $VF: renamed from: a () int
   private int method_80() {
      int var2 = (field_150 + 2) * field_151 - 1;

      int var1;
      while (var2 >= 0 && (var1 = this.field_180[var2] & 15) != 1 && var1 != 2) {
         var2--;
      }

      return var2;
   }

   // $VF: renamed from: b (int, int) int
   private int method_81(int var1, int var2) {
      int var3 = this.field_213[var1];
      int[] var6;
      int var4 = (var6 = this.field_174[var1])[5];
      int var5 = var6[0];
      var1 = var6[1];

      while (var3 == (var4 ^ 2) || (this.method_78(var5, var1, var3) & 15) >= 3 || var2 != 0 && var3 == 3) {
         var3 = var3 + 1 & 3;
      }

      return var3;
   }

   // $VF: renamed from: a (int, int, int, int, int, int) int
   private int method_82(int var1, int var2, int var3, int var4, int var5, int var6) {
      int var8 = 100000;
      int var9 = 0;
      var6 &= 32;

      for (int var7 = 0; var7 < 4; var7++) {
         if ((var5 ^ 2) != var7 && (this.method_78(var1, var2, var7) & 15) < 3 && (var6 == 0 || var7 != 3)) {
            int var10 = (var1 >> 3) + this.field_171[var7] - (var3 >> 3);
            int var11 = (var2 >> 3) + this.field_172[var7] - (var4 >> 3);
            var10 = var10 * var10 + var11 * var11;
            if (var8 >= var10) {
               var8 = var10;
               var9 = var7;
            }
         }
      }

      return var9;
   }

   // $VF: renamed from: a (int, boolean) void
   private void method_83(int var1, boolean var2) {
      int[] var3 = this.field_174[var1];
      if (field_173[14] != 0 && var1 == 0) {
         var3[8] = 2;
      } else if ((field_173[31] & 1) == 1) {
         var3[8] = 2;
      } else {
         var3[8] = 1;
      }

      if (var2 && var3[7] == 1) {
         var3[10] = 1;
      }
   }

   // $VF: renamed from: b () boolean
   private boolean method_84() {
      int var1 = this.field_225;
      this.method_123();
      if (this.method_128()) {
         this.method_71();
         return false;
      } else if (this.field_225 != Integer.MAX_VALUE) {
         return false;
      } else {
         if (var1 != Integer.MAX_VALUE) {
            field_173[0] = 2;
         }

         return true;
      }
   }

   // $VF: renamed from: r () void
   private void method_85() {
      if (!field_120) {
         field_173[8] = field_173[7];
         if (field_173[3] == 10) {
            this.method_115();
         }

         if (field_173[3] != 23) {
            field_173[7] = this.method_115() | this.field_274;
            field_173[9] = (field_173[7] ^ field_173[8]) & field_173[7];
         } else {
            field_173[7] = this.method_115() | this.field_274;
            field_173[9] = (field_173[7] ^ field_173[8]) & field_173[7];
         }
      } else {
         this.method_115();
      }

      switch (field_173[3]) {
         case 3:
            if (this.field_143 == this.field_139) {
               MenuUi.field_29 = 13;
               MenuUi.field_30 = 0;
               field_173[3] = 14;
               this.field_143 = null;
               return;
            }

            if (this.field_143 == this.field_140) {
               MenuUi.field_29 = 1;
               MenuUi.field_30 = 0;
               field_173[3] = 4;
               this.field_143 = null;
               MenuUi.field_35 = true;
               return;
            }

            if ((field_173[9] & 256) != 0 || this.field_143 == this.field_146 || field_119) {
               this.method_86();
               return;
            }
            break;
         case 4:
            int var121 = field_173[9];
            if (this.field_143 == this.field_146) {
               var121 = 256;
            }

            if (this.field_143 == this.field_137) {
               this.field_143 = null;
               this.method_69();
               return;
            }

            if (!MenuUi.method_13(var121)) {
               return;
            }

            this.field_143 = null;
            String var136;
            if ((var136 = (String)MenuUi.field_27[MenuUi.field_29][MenuUi.field_30]).equals(Localization.field_115[91])) {
               this.method_69();
            } else if (var136.equals(Localization.field_115[118])) {
               MenuUi.field_36 = true;
               this.method_86();
            } else if (var136.equals(Localization.field_115[101])) {
               field_173[3] = 52;
               MenuUi.field_29 = 15;
               MenuUi.field_32 = true;
               MenuUi.field_30 = 0;
            } else if (var136.equals(Localization.field_115[119])) {
               field_173[3] = 54;
               MenuUi.field_29 = 17;
               MenuUi.field_30 = 0;
            } else if (var136.equals(Localization.field_115[60]) && this.field_247) {
               this.method_102(LocalScores.format(0));
               field_173[3] = 58;
            } else if (var136.equals(Localization.field_115[128])) {
               this.method_102(this.field_285);
               field_173[3] = 58;
            }
            break;
         case 6:
            int var120 = field_173[9];
            if (this.field_143 == this.field_146) {
               var120 = 256;
            }

            if (MenuUi.method_13(var120) || this.field_143 == this.field_146) {
               this.field_143 = null;
               if (MenuUi.field_30 == 0) {
                  MessageScreens.field_66 = 15;
                  this.method_102(LocalScores.format(0));
                  field_173[3] = 58;
               } else {
                  field_173[3] = 4;
               }

               MenuUi.field_30 = field_164[25] != 0 ? 8 : 0;
               MenuUi.field_29 = 1;
            }

            return;
         case 7:
         case 8:
         case 9:
         case 15:
         case 19:
         case 20:
         case 21:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 50:
         case 51:
         default:
            break;
         case 10:
            if (this.field_219 == 1 && this.field_143 == this.field_138) {
               this.field_129.suspend();
               MenuUi.field_29 = 2;
               MenuUi.field_30 = 0;
               field_173[3] = 11;
               MenuUi.field_35 = false;
               this.field_143 = null;
               this.method_115();
               this.field_272 = 9999;
               this.field_249 = false;
               field_173[8] = field_173[7] = field_173[9] = 0;
               field_120 = true;
               return;
            }

            if (this.field_225 == Integer.MAX_VALUE) {
               this.method_67();
            }

            if (this.field_223 == 0) {
               int var51 = this.field_219 - 1;
               int var85 = 3;
               if (this.field_219 > 1) {
                  var51 += 5;
                  var85 += 5;
               }

               while (var51 <= var85) {
                  this.field_213[var51++] = Math.abs(this.field_211.nextInt()) & 3;
               }
            }

            if (this.field_219 > 1 && this.field_236 != null) {
               if (this.field_225 == Integer.MAX_VALUE) {
                  this.method_123();
                  if (this.method_128()) {
                     this.method_71();
                     return;
                  }

                  if (this.field_225 != Integer.MAX_VALUE && field_173[3] != 31) {
                     this.field_129.suspend();
                  }
               } else {
                  this.method_123();
                  if (this.method_128()) {
                     this.method_71();
                     return;
                  }

                  if (this.field_225 == Integer.MAX_VALUE) {
                     this.field_129.resume();
                     field_173[0] = 2;
                  }
               }
            }

            if (this.field_225 == Integer.MAX_VALUE) {
               int var52;
               if (this.field_219 > 1) {
                  for (var52 = this.field_219 - 1; var52 >= 0; var52--) {
                     if (this.method_117(var52) == 4) {
                        this.field_129.suspend();
                        MenuUi.field_29 = 2;
                        MenuUi.field_30 = 0;
                        field_173[3] = 11;
                        MenuUi.field_35 = false;
                        if (this.field_209 == -1) {
                           field_121 = false;
                           MenuUi.field_29 = 18;
                           MenuUi.field_30 = 0;
                           field_173[3] = 11;
                           this.method_119();
                           this.field_209 = this.field_208;
                        } else {
                           field_121 = true;
                        }

                        this.field_208 = 0;
                        this.field_275 = true;
                        break;
                     }
                  }
               } else {
                  var52 = -1;
               }

               if (var52 < 0) {
                  for (int var53 = 0; var53 < field_173[87]; var53++) {
                     if (this.field_219 <= 1) {
                        this.method_114();
                     }

                     try {
                        field_173[35] = field_173[35] + this.field_170[this.field_212[6]][field_173[33]];
                        if (field_173[35] >= 32) {
                           field_173[35] = field_173[35] - 32;
                           if (field_173[13] != 0) {
                              field_173[21] = 0;
                           } else if (field_173[27] == 2) {
                              int var118;
                              if ((var118 = this.field_213[4]) >= 0 && (this.method_78(field_173[16], field_173[17], var118) & 15) < 3) {
                                 field_173[20] = var118;
                              }

                              int var141 = this.method_78(field_173[16], field_173[17], field_173[20]) & 15;
                              if ((field_173[20] & 1) != 0) {
                                 if (var141 < 3 || (field_173[17] & 7) != 4) {
                                    field_173[17] = field_173[17] + (this.field_169[8][field_173[20]] << this.field_177);
                                 }

                                 if ((field_173[16] & 7) != 4) {
                                    if ((field_173[16] & 7) < 4) {
                                       field_173[16] = field_173[16] + this.field_178;
                                    } else {
                                       field_173[16] = field_173[16] - this.field_178;
                                    }
                                 }
                              } else {
                                 if (var141 < 3 || (field_173[16] & 7) != 4) {
                                    field_173[16] = field_173[16] + (this.field_169[7][field_173[20]] << this.field_177);
                                 }

                                 if ((field_173[17] & 7) != 4) {
                                    if ((field_173[17] & 7) < 4) {
                                       field_173[17] = field_173[17] + this.field_178;
                                    } else {
                                       field_173[17] = field_173[17] - this.field_178;
                                    }
                                 }
                              }

                              if (field_173[16] - 8 >= field_150 + 2 << 3) {
                                 field_173[16] = field_173[16] - (field_150 + 2 << 3);
                              }

                              if (field_173[16] - 8 < 0) {
                                 field_173[16] = field_173[16] + (field_150 + 2 << 3);
                              }

                              if (field_173[16] - 8 > field_150 << 3) {
                                 field_173[22] = field_173[16] - (field_150 + 2 << 3);
                              } else {
                                 field_173[22] = field_173[16];
                              }

                              int var6;
                              int var7;
                              if ((var7 = (var6 = this.method_79(field_173[16], field_173[17])) & 15) == 1 || var7 == 2) {
                                 field_173[4]++;
                                 if (this.field_219 > 1 && field_173[4] > field_154 - 16) {
                                    field_173[97] = 1800;
                                 }

                                 field_173[5]++;
                                 field_173[29] = 0;
                                 field_173[52] = 1;
                                 var118 = field_173[16] >> 3;
                                 int var134 = field_173[17] >> 3;
                                 if (var118 >= field_150 + 2) {
                                    var118 = 0;
                                 }

                                 if (var118 < 0) {
                                    var118 = field_150 + 1;
                                 }

                                 this.field_180[var134 * (field_150 + 2) + var118] = var6 & 240;
                                 this.method_105(this.field_183, 0, var118 * 10, var134 * 10);
                                 if (!this.field_129.isPlaying(this.field_130)) {
                                    this.field_130 = ~this.field_130 & 3;
                                    this.field_129.play(this.field_130);
                                 }

                                 if (var7 == 1) {
                                    field_173[11]++;
                                    field_173[36] = 2;
                                 } else if (var7 == 2) {
                                    field_173[11] = field_173[11] + 5;
                                    field_173[43] = 0;
                                    field_173[30] = 0;
                                    field_173[33] = 1;
                                    field_173[36] = 7;
                                    this.field_174[0][9] = 1;
                                    this.field_174[1][9] = 1;
                                    this.field_174[2][9] = 1;
                                    this.field_174[3][9] = 1;
                                 }
                              }

                              if (field_173[23] == 0 && field_155[3][0] == field_173[4] || field_173[23] == 1 && field_155[3][1] == field_173[4]) {
                                 field_173[24] = 10000;
                                 field_173[88] = 2;
                                 field_173[23]++;
                              }

                              if (field_173[88] == 2 && field_173[16] == (field_155[1][0] << 3) + 4 && field_173[17] == (field_155[1][1] << 3) + 4) {
                                 field_173[24] = 3000;
                                 field_173[88] = 1;
                                 field_173[11] = field_173[11] + this.field_212[5] * 10;
                                 field_173[52] = 1;
                                 this.field_129.play(5);
                              }

                              field_173[63] = var141;
                           }
                        }
                     } catch (Exception var28) {
                     }

                     try {
                        this.method_74();
                     } catch (Exception var26) {
                     }

                     this.method_77();
                  }

                  if (this.field_219 == 1 && field_173[37] == 0 && field_173[11] >= 1000) {
                     field_173[28]++;
                     field_173[0] = 2;
                     field_173[37]++;
                     this.field_129.play(6);
                  }

                  if (field_173[13] == 0) {
                     if (field_173[88] != 0 && field_173[24] == 0) {
                        field_173[88] = 0;
                     }
                  } else if (field_173[85] == 0) {
                     field_173[13] = 0;
                     field_173[0] = 2;
                  }

                  if (this.field_219 > 1 && field_173[1] == 24 && field_173[4] > field_154 - 16 && (var52 = this.method_80()) > 0) {
                     if (field_173[97] <= 0) {
                        this.field_180[var52] = this.field_180[var52] & -4;
                        this.method_105(this.field_183, 0, var52 % (field_150 + 2) * 10, var52 / (field_150 + 2) * 10);
                        field_173[4]++;
                        field_173[5]++;
                        field_173[52] = 1;
                        field_173[97] = 1800;
                     } else if ((this.field_180[var52] & 3) != 2) {
                        if ((field_173[6] & 3) == 0) {
                           this.method_105(this.field_183, 1, var52 % (field_150 + 2) * 10, var52 / (field_150 + 2) * 10);
                        } else if ((field_173[6] & 3) == 2) {
                           this.method_105(this.field_183, 0, var52 % (field_150 + 2) * 10, var52 / (field_150 + 2) * 10);
                        }
                     }
                  }

                  if ((field_173[1] == 13 || field_173[1] == 24) && field_173[4] == field_154) {
                     field_173[43] = 0;
                     field_173[1] = 3;
                  }

                  if (field_173[12] < 3) {
                     int var86;
                     if (field_173[12] < this.field_219 - 1) {
                        var86 = 200;
                     } else if (field_173[25] < 2) {
                        var86 = 4000;
                     } else {
                        var86 = 3000;
                     }

                     if (field_173[38] == 2) {
                        if (field_173[5] >= this.field_169[10][field_173[12]] || field_173[29] >= var86) {
                           this.field_174[1 + field_173[12]++][11] = 1;
                           field_173[29] = 0;
                        }
                     } else if (field_173[5] >= this.field_169[this.field_212[7]][field_173[12]] || field_173[29] >= var86) {
                        this.field_174[1 + field_173[12]++][11] = 1;
                        field_173[29] = 0;
                     }
                  }

                  if (field_173[31] < 7 && field_173[32] >= this.field_170[this.field_212[3] + 4][field_173[31]] * 1000) {
                     field_173[31]++;

                     for (int var55 = 0; var55 < 4; var55++) {
                        if (this.field_174[var55][8] == 1 || this.field_174[var55][8] == 2) {
                           this.method_83(var55, true);
                        }
                     }
                  }

                  int var87 = 0;

                  for (int var56 = 0; var56 < 4; var56++) {
                     if (this.field_174[var56][7] == 2) {
                        var87 = 1;
                     }
                  }

                  field_173[33] = var87;
                  var87 = 0;

                  for (int var57 = 0; var57 < 4; var57++) {
                     var87 += this.field_174[var57][14];
                  }

                  field_173[14] = 0;
                  if (this.field_219 > 1 && var87 == 0) {
                     for (int var58 = 1; var58 < 3; var58++) {
                        if (field_173[4] >= field_154 - this.field_212[var58]) {
                           field_173[14] = var58;
                        }
                     }
                  }

                  if (this.field_221 != 4) {
                     int var59;
                     if (this.field_174[this.field_221][8] != 1 && this.field_174[this.field_221][8] != 2 && this.field_174[this.field_221][8] != 3) {
                        var59 = 70;
                     } else {
                        var59 = 110;
                     }

                     if ((var87 = field_173[89]) < var59) {
                        var87++;
                     } else if (var87 > var59) {
                        var87--;
                     }

                     field_173[89] = var87;
                     if (this.field_174[this.field_221][15] > 0) {
                        var59 = 110;
                     } else {
                        var59 = 70;
                     }

                     if ((var87 = field_173[90]) < var59) {
                        var87++;
                     } else if (var87 > var59) {
                        var87--;
                     }

                     field_173[90] = var87;
                     return;
                  }
               }
            }
            break;
         case 11:
            if (this.field_219 > 1) {
               int var50 = this.field_225;
               this.method_123();
               if (this.method_128()) {
                  this.method_71();
                  return;
               }

               if (this.field_225 != Integer.MAX_VALUE) {
                  break;
               }

               if (var50 != Integer.MAX_VALUE) {
                  field_173[0] = 2;
               }

               int var116;
               if ((var116 = this.method_116()) == 32) {
                  this.field_143 = this.field_137;
               } else if (var116 == 4) {
                  this.field_143 = this.field_146;
               }
            }

            if (this.field_143 == this.field_137) {
               this.field_143 = null;
               this.field_275 = false;
               this.field_175 = 0;
               if (this.field_219 > 1) {
                  this.field_208 = this.field_209;
                  this.field_209 = -1;
               }

               field_173[3] = 10;
               field_173[0] = 2;
               this.field_129.resume();
               this.field_143 = null;
               return;
            }

            int var117 = field_173[9];
            if (this.field_143 == this.field_146) {
               var117 = 256;
            }

            if (!MenuUi.method_13(var117)) {
               return;
            }

            this.field_143 = null;
            String var133;
            if ((var133 = (String)MenuUi.field_27[MenuUi.field_29][MenuUi.field_30]).equals(Localization.field_115[98])) {
               this.field_275 = false;
               this.field_175 = 0;
               if (this.field_219 > 1) {
                  this.field_208 = this.field_209;
                  this.field_209 = -1;
               }

               field_173[3] = 10;
               field_173[0] = 2;
               this.field_129.resume();
               this.field_143 = null;
            } else if (var133.equals(Localization.field_115[101])) {
               field_173[3] = 52;
               MenuUi.field_29 = 15;
               MenuUi.field_32 = true;
               MenuUi.field_30 = 0;
            } else {
               if (!var133.equals(Localization.field_115[119])) {
                  if (var133.equals(Localization.field_115[91])) {
                     MenuUi.field_29 = 4;
                     MenuUi.field_30 = 0;
                     field_173[3] = 18;
                     field_173[8] = field_173[7] = field_173[9] = 0;
                     field_120 = true;
                     this.method_115();
                  }

                  return;
               }

               field_173[3] = 54;
               MenuUi.field_29 = 17;
               MenuUi.field_30 = 0;
            }
            break;
         case 12:
         case 34:
            if (this.field_219 > 1) {
               this.method_123();
               if (this.method_128()) {
                  this.method_71();
                  return;
               }

               if (this.field_225 != Integer.MAX_VALUE) {
                  break;
               }

               field_173[9] = this.method_116();
            }

            if (this.field_143 == this.field_146) {
               field_173[9] = 256;
               this.field_143 = null;
            }

            if (this.field_143 == this.field_137) {
               this.field_192 = this.field_193 = null;
               System.gc();
               MenuUi.field_29 = 5;
               MenuUi.field_30 = field_173[3] == 12 ? 0 : 1;
               field_173[3] = 22;
               this.field_143 = null;
               return;
            }

            if (field_173[85] > 2000) {
               int var84 = field_173[9];
               int var49 = this.field_242;
               if ((var84 & 256) != 0 || field_119) {
                  field_119 = false;
                  field_173[85] = 2000;
               } else if ((var84 & 2) == 0 && this.field_272 != -1) {
                  if ((var84 & 64) == 0 && this.field_272 != -2) {
                     if ((var84 & 4) == 0 && this.field_272 != -3) {
                        if (((var84 & 32) != 0 || this.field_272 == -4) && (this.field_242 & 1) == 0) {
                           this.field_242++;
                        }
                     } else if ((this.field_242 & 1) != 0) {
                        this.field_242--;
                     }
                  } else if (this.field_242 < 2) {
                     this.field_242 += 2;
                  }
               } else if (this.field_242 > 1) {
                  this.field_242 -= 2;
               }

               if (var49 != this.field_242) {
                  field_173[47] = method_58(field_164, 1 + (this.field_242 << 2), 4);
                  return;
               }
            } else if (field_173[85] == 0) {
               this.method_66(this.field_242);
               if (this.field_219 > 1) {
                  this.field_275 = false;
                  method_68();
               }

               this.field_192 = this.field_193 = null;
               System.gc();
               return;
            }
            break;
         case 13:
            if (this.field_225 == Integer.MAX_VALUE) {
               this.method_67();
            }

            if (this.field_219 > 1) {
               int var48 = this.field_225;
               this.method_123();
               if (this.method_128()) {
                  this.method_71();
                  return;
               }

               if (this.field_225 == Integer.MAX_VALUE && var48 != Integer.MAX_VALUE) {
                  field_173[0] = 2;
                  return;
               }
            }
            break;
         case 14:
            int var115 = field_173[9];
            if (this.field_143 == this.field_146) {
               var115 = 256;
            }

            if (this.field_143 == this.field_137) {
               this.field_143 = null;
               field_173[3] = 3;
            }

            if (!MenuUi.method_13(var115)) {
               return;
            }

            this.field_143 = null;
            if (MenuUi.field_30 != 0) {
               this.method_69();
               return;
            }

            this.method_143();
            break;
         case 16:
            if (this.field_219 > 1) {
               this.method_123();
               if (this.method_128()) {
                  this.method_71();
                  return;
               }

               if (this.field_225 != Integer.MAX_VALUE) {
                  break;
               }

               field_173[9] = this.method_116();
            }

            int var114 = field_173[9];
            if (this.field_143 == this.field_146) {
               var114 = 256;
            }

            if (this.field_143 == this.field_137) {
               this.field_143 = null;
               this.field_275 = false;
               this.field_175 = 0;
               if (this.field_219 > 1) {
                  this.field_208 = this.field_209;
                  this.field_209 = -1;
               }

               field_173[3] = 10;
               field_173[0] = 2;
               this.field_129.resume();
               this.field_143 = null;
               return;
            }

            if (!MenuUi.method_13(var114)) {
               return;
            }

            this.field_143 = null;
            String var132;
            if ((var132 = (String)MenuUi.field_27[MenuUi.field_29][MenuUi.field_30]).equals(Localization.field_115[98])) {
               this.field_275 = false;
               this.field_175 = 0;
               if (this.field_219 > 1) {
                  this.field_208 = this.field_209;
                  this.field_209 = -1;
               }

               field_173[3] = 10;
               field_173[0] = 2;
               this.field_129.resume();
               this.field_143 = null;
            } else if (var132.equals(Localization.field_115[101])) {
               field_173[3] = 52;
               MenuUi.field_29 = 15;
               MenuUi.field_32 = true;
               MenuUi.field_30 = 0;
            } else {
               if (!var132.equals(Localization.field_115[119])) {
                  if (var132.equals(Localization.field_115[91])) {
                     MenuUi.field_29 = 4;
                     MenuUi.field_30 = 0;
                     field_173[3] = 18;
                  }

                  return;
               }

               field_173[3] = 54;
               MenuUi.field_29 = 17;
               MenuUi.field_30 = 0;
            }
            break;
         case 17:
            if (this.field_219 > 1) {
               this.method_123();
               if (this.method_128()) {
                  this.method_71();
                  return;
               }

               if (this.field_225 != Integer.MAX_VALUE) {
                  break;
               }

               if (this.method_116() == 256) {
                  this.field_143 = this.field_137;
               }
            }
         case 5:
         case 32:
            if (!MenuUi.field_35 && this.field_219 > 1) {
               this.method_84();
            }

            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = this.field_157;
               if (this.field_158 == -1) {
                  MenuUi.field_30 = 0;
               } else if (this.field_158 != -2) {
                  MenuUi.field_30 = MenuUi.method_16(this.field_158);
               }

               field_173[3] = this.field_156;
               MessageScreens.field_68 = 0;
               this.field_143 = null;
               return;
            }

            MessageScreens.method_27(field_173[7]);
            return;
         case 18:
            if (this.field_219 > 1) {
               this.method_123();
               if (this.method_128()) {
                  this.method_71();
                  return;
               }

               if (this.field_225 != Integer.MAX_VALUE) {
                  break;
               }

               int var112 = this.method_116();
               if (!field_121 && (var112 & 32) != 0) {
                  this.field_275 = false;
                  this.field_175 = 0;
                  if (this.field_219 > 1) {
                     this.field_208 = this.field_209;
                     this.field_209 = -1;
                  }

                  field_173[3] = 10;
                  field_173[0] = 2;
                  this.field_129.resume();
                  this.field_143 = null;
                  return;
               }
            }

            int var113 = field_173[9];
            if (this.field_143 == this.field_146) {
               var113 = 256;
            }

            if (this.field_143 == this.field_137) {
               this.field_143 = null;
               if (!field_121 && this.field_219 != 1) {
                  MenuUi.field_29 = 18;
               } else {
                  MenuUi.field_29 = 2;
               }

               MenuUi.field_30 = MenuUi.method_16(91);
               field_173[3] = 11;
            }

            if (!MenuUi.method_13(var113)) {
               return;
            }

            this.field_143 = null;
            if (MenuUi.field_30 != 0) {
               if (!field_121 && this.field_219 != 1) {
                  MenuUi.field_29 = 18;
               } else {
                  MenuUi.field_29 = 2;
               }

               MenuUi.field_30 = MenuUi.method_16(91);
               field_173[3] = 11;
               MenuUi.field_35 = false;
               return;
            }

            if (this.field_219 > 1) {
               this.method_125();
               this.method_126();
            }

            this.method_69();
            break;
         case 22:
            if (this.field_143 == this.field_137) {
               if (MenuUi.field_36) {
                  MenuUi.field_29 = 1;
                  MenuUi.field_30 = MenuUi.method_16(118);
                  field_173[3] = 4;
               } else {
                  this.method_69();
               }

               this.field_143 = null;
               return;
            }

            int var111 = field_173[9];
            if (this.field_143 == this.field_146) {
               var111 = 256;
            }

            if (!MenuUi.method_13(var111)) {
               return;
            }

            this.field_143 = null;
            this.field_222 = 0;
            if (MenuUi.field_30 == 0) {
               this.field_166 = this.field_167;
               this.field_219 = 1;
               this.field_223 = 0;
               this.method_91(field_164, 28, this.field_231, 0);
               this.field_175 = 50;
               this.field_242 = 0;
               this.method_88(false);
            } else {
               this.field_166 = this.field_168;
               this.method_91(field_164, 28, NicknameEditor.field_60, 0);
               if (!method_87()) {
                  MenuUi.field_29 = 7;
                  MenuUi.field_30 = 0;
                  field_173[3] = 24;
                  return;
               }

               field_173[15] = 7;
               if (this.field_247) {
                  this.field_287 = false;
               }

               this.method_89();
            }
            break;
         case 23:
            if (this.field_143 == this.field_137) {
               field_173[8] = field_173[7] = field_173[9] = 0;
               field_120 = true;
               if (field_173[15] == 52) {
                  MenuUi.field_29 = 15;
                  MenuUi.field_32 = true;
                  MenuUi.field_30 = MenuUi.method_16(59);
                  field_173[3] = 52;
               } else if (this.field_287) {
                  this.field_287 = false;
                  MenuUi.field_29 = 1;
                  MenuUi.field_30 = MenuUi.method_16(60);
                  field_173[3] = 4;
               } else {
                  MenuUi.field_29 = 5;
                  MenuUi.field_30 = 1;
                  field_173[3] = 22;
               }

               this.field_143 = null;
               return;
            }

            boolean var109;
            if ((var109 = method_87()) != this.field_122) {
               MenuUi.field_49 = 1;
            }

            this.field_122 = var109;
            MenuUi.field_49 = 1;
            if (var109 && (Command)this.field_278.firstElement() != this.field_142) {
               this.method_122(this.field_142, this.field_137);
            } else if (!var109 && (Command)this.field_278.firstElement() == this.field_142) {
               this.method_122(this.field_147, this.field_137);
            }

            int var131 = field_173[9];
            if (this.field_143 == this.field_147) {
               var131 = 256;
            }

            if (this.field_143 != this.field_147 && (field_173[9] & 256) == 0 || var109) {
               NicknameEditor.method_13(var131);
               this.field_143 = null;
               return;
            }

            this.field_143 = null;
            field_173[8] = field_173[7] = field_173[9] = 0;
            field_120 = true;
            char[] var110 = NicknameEditor.field_60;
            String var140 = new String(var110).trim();

            for (int var47 = 6; var47 >= 0; var47--) {
               var110[var47] = ' ';
            }

            var140.getChars(0, var140.length(), var110, 0);
            this.field_274 = 0;
            this.method_92(var110, 0, field_164, 28);
            this.method_60();
            if (this.field_287 && this.field_247) {
               MenuUi.field_32 = false;
               MenuUi.field_29 = 23;
               MenuUi.field_30 = 0;
               this.method_102(LocalScores.format(0));
               field_173[3] = 58;
               MenuUi.field_49 = 2;
               return;
            }

            if (field_173[15] == 52) {
               MenuUi.field_29 = 15;
               MenuUi.field_32 = true;
               MenuUi.field_30 = MenuUi.method_16(59);
               field_173[3] = 52;
               if (field_173[1] == 23) {
                  field_173[15] = 2;
                  return;
               }
            } else {
               MenuUi.field_29 = 7;
               MenuUi.field_30 = 0;
               field_173[3] = 24;
            }
            break;
         case 24:
            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = 5;
               MenuUi.field_30 = 1;
               field_173[3] = 22;
               this.field_143 = null;
               return;
            }

            int var108 = field_173[9];
            if (this.field_143 == this.field_146) {
               var108 = 256;
            }

            if (!MenuUi.method_13(var108)) {
               return;
            }

            this.field_143 = null;

            try {
               switch (MenuUi.field_30) {
                  case 0:
                     field_173[85] = 300000;
                     this.method_129();
                     return;
                  case 1:
                     field_173[85] = 300000;
                     this.method_130();
               }
            } catch (SecurityException var30) {
               this.method_70(11);
            } catch (Exception var31) {
               this.method_70(13);
               return;
            }
            break;
         case 25:
            if (this.field_143 == this.field_137) {
               if (this.field_233 != null) {
                  this.field_233.cancel();
               }

               this.field_233 = null;
               System.gc();
               MenuUi.field_29 = 7;
               field_173[3] = 24;
               this.field_143 = null;
               return;
            }

            if (this.field_232) {
               LanHost[] found = this.field_233.hosts();
               this.field_238 = found;
               this.field_239 = 0;
               this.field_241 = found.length;
               field_173[3] = 26;
               return;
            }

            if (field_173[85] <= 0) {
               if (this.field_233 != null) {
                  this.field_233.cancel();
               }

               this.field_233 = null;
               this.method_70(13);
               System.gc();
               return;
            }

            int var82;
            if ((field_173[6] & 3) == 0) {
               for (int var46 = (var82 = MessageScreens.field_63[5].length) - 8; var46 < var82; var46++) {
                  MessageScreens.field_63[5][var46] = field_126[(field_173[6] + var46) % field_126.length];
               }
            }
            break;
         case 26:
            if (this.field_143 == this.field_137) {
               this.field_238 = null;
               this.field_233 = null;
               System.gc();
               MenuUi.field_29 = 7;
               MenuUi.field_30 = 0;
               field_173[3] = 24;
               this.field_143 = null;
               return;
            }

            if (this.field_232) {
               if (this.field_241 > 0) {
                  MenuUi.field_27[10] = new Object[this.field_241 + 2];

                  for (int var45 = this.field_241 - 1; var45 >= 0; var45--) {
                     int var79 = MessageScreens.field_63[5].length - 8 + var45;
                     String var106 = MessageScreens.field_63[5][var79];
                     MessageScreens.field_63[5][var79] = null;
                     if (var106.indexOf(45) != -1) {
                        var106 = (var106 = this.field_238[var45].getName()).substring(0, var106.indexOf(95));
                        var79 = 0;
                     } else {
                        var79 = "%C%".length();
                     }

                     char[] var130;
                     (var130 = new char[10])[0] = '[';
                     var130[1] = (char)(var45 < 3 ? 42 : 32);
                     var130[2] = ']';
                     var106.getChars(var79, var106.length(), var130, 3);
                     var79 = 3 + (var106.length() - var79);

                     while (var79 < var130.length) {
                        var130[var79++] = ' ';
                     }

                     MenuUi.field_27[10][var45] = var130;
                  }

                  MenuUi.field_27[10][this.field_241] = Localization.field_115[22];
                  MenuUi.field_29 = 10;
                  MenuUi.field_30 = this.field_241;
                  field_173[3] = 28;
               } else {
                  this.field_238 = null;
                  MenuUi.field_29 = 8;
                  MenuUi.field_30 = 0;
                  field_173[3] = 27;
               }

               this.field_233 = null;
               System.gc();
               return;
            }

            if (field_173[85] <= 0) {
               this.field_238 = null;
               this.field_233 = null;
               this.method_70(13);
               System.gc();
               return;
            }

            if ((field_173[6] & 3) == 0) {
               for (int var44 = 7; var44 >= 0; var44--) {
                  int var78 = MessageScreens.field_63[5].length - 8 + var44;
                  String var103;
                  if (var44 < this.field_241) {
                     if ((var103 = MessageScreens.field_63[5][var78]).indexOf(45) != -1) {
                        var103 = (var103 = this.field_238[var44].getName()).substring(0, var103.indexOf(95));
                        var103 = "%C%" + var103;
                     }
                  } else {
                     var103 = field_126[(field_173[6] + var78) % field_126.length];
                  }

                  MessageScreens.field_63[5][var78] = var103;
               }
            }
            break;
         case 27:
         case 30:
            int var102 = field_173[9];
            if (this.field_143 == this.field_146) {
               var102 = 256;
            }

            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = 7;
               if (field_173[3] == 30) {
                  MenuUi.field_30 = 1;
               }

               field_173[3] = 24;
               this.field_143 = null;
            } else {
               if (!MenuUi.method_13(var102)) {
                  return;
               }

               this.field_143 = null;

               try {
                  if (field_173[3] == 27) {
                     this.method_129();
                     return;
                  }

                  this.method_130();
               } catch (SecurityException var32) {
                  this.method_70(11);
               } catch (Exception var33) {
                  this.method_70(13);
                  return;
               }
            }
            break;
         case 28:
            int var98 = field_173[9];
            if (this.field_143 == this.field_146) {
               var98 = 256;
            }

            if (this.field_143 == this.field_137) {
               this.field_238 = null;
               MenuUi.field_29 = 7;
               MenuUi.field_30 = 0;
               field_173[3] = 24;
               MenuUi.field_27[10] = null;
               this.field_143 = null;
            } else {
               if (!MenuUi.method_13(var98)) {
                  return;
               }

               this.field_143 = null;
               this.field_219 = 1;

               for (int var39 = this.field_241 - 1; var39 >= 0; var39--) {
                  if (((char[])MenuUi.field_27[10][var39])[1] == '*') {
                     this.field_219++;
                  }
               }

               if (MenuUi.field_30 == this.field_241) {
                  if (this.field_219 <= 1) {
                     return;
                  }

                  this.field_236 = new LanLink[this.field_219 - 1];
                  this.field_226 = new byte[6];
                  this.field_227 = new byte[14];
                  System.arraycopy(NicknameEditor.field_60, 0, this.field_231, 0, 7);
                  int var70 = 0;
                  int var40 = 1;
                  Vector var139;
                  (var139 = new Vector(5))
                     .addElement(this.method_144("NAMCO-VS-LOOP") + '_' + this.method_144("NAMCO-VS-WAIT") + '_' + this.method_144("MIDlet-Version"));

                  for (; var40 < this.field_219; var70++) {
                     char[] var128;
                     if ((var128 = (char[])MenuUi.field_27[10][var70])[1] == '*') {
                        try {
                           this.field_236[var40 - 1] = LanLink.connect(this.field_238[var70]);
                           System.arraycopy(var128, 3, this.field_231, var40 * 7, 7);
                           String var99 = this.field_238[var70].getName();
                           var139.addElement(var99.substring(var99.indexOf(95) + 1));
                           var40++;
                        } catch (SecurityException var21) {
                           this.method_125();
                           this.method_126();
                           this.method_70(11);
                           return;
                        } catch (Exception var22) {
                           this.field_219--;
                        }
                     }
                  }

                  if (this.field_219 <= 1) {
                     this.method_71();
                  } else {
                     if (this.field_236.length != this.field_219 - 1) {
                        LanLink[] var71 = new LanLink[this.field_219 - 1];
                        System.arraycopy(this.field_236, 0, var71, 0, var71.length);
                        this.field_236 = var71;
                     }

                     field_173[87] = Integer.MIN_VALUE;
                     field_173[86] = Integer.MIN_VALUE;
                     this.field_131 = false;

                     for (int var41 = this.field_219 - 1; var41 >= 0; var41--) {
                        String var100;
                        if (!(var100 = (String)var139.elementAt(var41)).substring(var100.lastIndexOf(95) + 1).equals(this.method_144("MIDlet-Version"))) {
                           this.field_131 = true;
                        }

                        for (int var72 = this.field_219 - 3; var72 >= 0; var72--) {
                           var100 = var100.substring(var100.indexOf(95) + 1, var100.length());
                        }

                        if ((var70 = Integer.parseInt(var100.substring(0, var100.indexOf(95)))) > field_173[87]) {
                           field_173[87] = var70;
                        }
                     }

                     for (int var42 = this.field_219 - 1; var42 >= 0; var42--) {
                        String var101 = (String)var139.elementAt(var42);

                        for (int var74 = 4 + this.field_219 - 3; var74 >= 0; var74--) {
                           var101 = var101.substring(var101.indexOf(95) + 1, var101.length());
                        }

                        var70 = var101.indexOf(95);
                        if ((var70 = Integer.parseInt(var101.substring(0, var70 != -1 ? var70 : var101.length()))) > field_173[86]) {
                           field_173[86] = var70;
                        }
                     }

                     this.method_127();
                     byte[] var77 = new byte[this.field_219 * 7 << 1];
                     this.field_227[0] = (byte)(this.field_219 | (this.field_131 ? 8 : 0));

                     for (int var43 = this.field_219 - 1; var43 > 0; var43--) {
                        this.field_227[1] = (byte)var43;
                        this.field_227[2] = (byte)field_173[87];
                        this.field_227[3] = (byte)field_173[86];
                        this.method_134(var43 - 1);
                        this.method_92(this.field_231, var43 * 7, var77, var43 * 14);
                     }

                     this.method_92(this.field_231, 0, var77, 0);
                     this.method_133(-1, var77, var77.length);
                     this.method_90();
                  }

                  this.field_238 = null;
                  MenuUi.field_27[10] = null;
               } else {
                  char[] var129;
                  if ((var129 = (char[])MenuUi.field_27[10][MenuUi.field_30])[1] == '*') {
                     var129[1] = ' ';
                     return;
                  }

                  if (this.field_219 >= 4) {
                     return;
                  }

                  var129[1] = '*';
               }
            }
            break;
         case 29:
            if (this.field_143 == this.field_137) {
               MessageScreens.field_63[6][MessageScreens.field_63[6].length - 1] = "%C%" + Localization.field_115[29];
               this.repaint();
               this.serviceRepaints();

               try {
                  this.field_235.close();
               } catch (Exception var8) {
               }

               this.field_235 = null;
               this.method_125();
               this.method_126();
               MenuUi.field_29 = 7;
               MenuUi.field_30 = 1;
               field_173[3] = 24;
               MessageScreens.field_63[6][MessageScreens.field_63[6].length - 1] = field_126[0];
               this.field_143 = null;
               return;
            }

            boolean var97;
            synchronized (this) {
               var97 = this.field_232;
            }

            if (!var97) {
               if (field_173[85] > 0) {
                  MessageScreens.field_63[6][MessageScreens.field_63[6].length - 1] = field_126[field_173[6] % field_126.length];
                  synchronized (this) {
                     try {
                        this.wait(50L);
                     } catch (Exception var9) {
                     }

                     return;
                  }
               }

               MessageScreens.field_63[6][MessageScreens.field_63[6].length - 1] = "%C%" + Localization.field_115[29];
               this.repaint();
               this.serviceRepaints();

               try {
                  this.field_235.close();
               } catch (Exception var24) {
               }

               this.field_235 = null;
               this.method_125();
               this.method_126();
               this.method_125();
               this.method_126();
               MenuUi.field_30 = 0;
               MenuUi.field_29 = 9;
               field_173[3] = 30;
               MessageScreens.field_63[6][MessageScreens.field_63[6].length - 1] = field_126[0];
            } else {
               if (this.field_226[0] != 0) {
                  this.method_71();
                  return;
               }

               try {
                  this.field_235.close();
               } catch (Exception var23) {
               }

               this.field_235 = null;
               this.field_237 = "host";
               if (this.field_237 != null) {
                  this.method_127();

                  while (this.method_132(0) == 0 && !this.method_128()) {
                  }

                  this.field_131 = (this.field_226[0] & 8) != 0;
                  this.field_226[0] = (byte)(this.field_226[0] & -9);
                  this.field_219 = this.field_226[0];
                  this.field_222 = this.field_226[1];
                  field_173[87] = this.field_226[2];
                  field_173[86] = this.field_226[3];
                  byte[] var127 = new byte[this.field_219 * 7 << 1];
                  this.method_131(0, var127, var127.length);

                  for (int var38 = this.field_219 - 1; var38 >= 0; var38--) {
                     this.method_91(var127, var38 * 14, this.field_231, var38 * 7);
                  }

                  if (!this.method_128()) {
                     this.method_90();
                     return;
                  }

                  this.method_71();
               } else {
                  this.method_71();
               }
            }
            break;
         case 31:
            if (this.field_143 == this.field_137) {
               this.method_69();
               return;
            }
            break;
         case 33:
         case 35:
            this.method_123();
            if (this.method_128()) {
               this.method_71();
               return;
            }

            if (this.field_225 == Integer.MAX_VALUE) {
               if (field_173[85] <= 0) {
                  if (field_173[3] == 33) {
                     this.field_242 = 0;
                     this.method_88(true);
                     return;
                  }

                  field_173[85] = 4000;
                  field_173[3] = 36;
                  return;
               }

               int var10000 = field_173[6];
               if (true) {
                  if (++field_173[80] >= 4) {
                     field_173[80] = 0;
                  }

                  if (++field_173[83] >= 2) {
                     field_173[83] = 0;
                     return;
                  }
               }
            }
            break;
         case 36:
            this.method_123();
            if (this.method_128()) {
               this.method_71();
               return;
            }

            if (this.field_225 == Integer.MAX_VALUE && field_173[85] <= 0) {
               field_173[3] = 10;
               this.method_114();
               field_173[0] = 2;
               return;
            }
            break;
         case 37:
            this.method_123();
            if (this.method_128()) {
               this.method_71();
               return;
            }

            if (this.field_225 == Integer.MAX_VALUE && (this.method_116() & 256) != 0) {
               MenuUi.field_30 = 0;
               MenuUi.field_29 = 11;
               this.field_129.stopAndForgetLooping();
               field_173[3] = 38;
               return;
            }
            break;
         case 38:
            this.method_123();
            if (this.method_128()) {
               this.method_71();
               if (MenuUi.field_30 == 1) {
                  this.method_69();
                  return;
               }
            } else if (this.field_225 == Integer.MAX_VALUE) {
               int var69;
               if (((var69 = this.method_116()) & 256) != 0) {
                  if (MenuUi.field_30 == 0) {
                     this.field_129.reset();
                     this.method_88(true);
                     return;
                  }

                  this.method_125();
                  this.method_126();
                  this.method_69();
                  return;
               }

               if ((var69 & 66) != 0) {
                  MenuUi.field_30 ^= 1;
               }
            }
            break;
         case 52:
            if (MenuUi.field_35 || this.field_219 <= 1 || this.method_84()) {
               int var96 = field_173[9];
               if (this.field_143 == this.field_146) {
                  var96 = 256;
                  this.field_143 = null;
               }

               if (this.field_143 == this.field_137) {
                  MenuUi.field_29 = MenuUi.field_35 ? 1 : 2;
                  MenuUi.field_30 = MenuUi.method_16(101);
                  field_173[3] = MenuUi.field_35 ? 4 : 11;
                  this.field_143 = null;
                  return;
               }

               if (!MenuUi.method_13(var96)) {
                  return;
               }

               String var126;
               if ((var126 = (String)MenuUi.field_27[MenuUi.field_29][MenuUi.field_30]).equals(Localization.field_115[104])) {
                  MenuUi.field_27[15][MenuUi.field_30] = Localization.field_115[105];
                  MenuUi.method_14();
                  field_173[40] = 1;
                  this.field_129.disableSound();
                  field_164[0] = (byte)field_173[40];
               } else if (var126.equals(Localization.field_115[105])) {
                  MenuUi.field_27[15][MenuUi.field_30] = Localization.field_115[104];
                  MenuUi.method_14();
                  field_173[40] = 2;
                  this.field_129.enableSound();
                  field_164[0] = (byte)field_173[40];
                  this.field_129.reset();
                  this.field_129.play(3);
               } else if (var126.equals(Localization.field_115[59])) {
                  field_173[15] = 52;
                  if (this.field_247) {
                     this.field_287 = false;
                  }

                  this.method_89();
               } else {
                  if (!var126.equals(Localization.field_115[103])) {
                     if (var126.equals(Localization.field_115[102])) {
                        field_173[3] = 53;
                        MenuUi.field_29 = 16;
                        MenuUi.field_30 = 0;
                     }

                     return;
                  }

                  field_173[3] = 55;
                  MenuUi.field_26[0] = new String[]{Localization.field_115[103]};
                  MenuUi.field_29 = 0;
                  if (field_164[27] != -1 && field_164[27] < this.field_279.length) {
                     MenuUi.field_30 = field_164[27];
                  } else {
                     MenuUi.field_30 = 1;
                  }
               }
            }
            break;
         case 53:
            int var95 = field_173[9];
            if (this.field_143 == this.field_146) {
               var95 = 256;
            }

            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = 15;
               MenuUi.field_32 = true;
               MenuUi.field_30 = MenuUi.method_16(102);
               field_173[3] = 52;
               this.field_143 = null;
               return;
            }

            if (!MenuUi.method_13(var95)) {
               return;
            }

            String var125;
            if (!(var125 = (String)MenuUi.field_27[MenuUi.field_29][MenuUi.field_30]).equals(Localization.field_115[56])) {
               if (var125.equals(Localization.field_115[66])) {
                  MenuUi.field_29 = 15;
                  MenuUi.field_32 = true;
                  MenuUi.field_30 = MenuUi.method_16(102);
                  field_173[3] = 52;
               }

               return;
            }

            if (this.field_247) {
               this.field_285 = "";

               for (int var1 = 0; var1 < MenuUi.field_27[1].length; var1++) {
                  if (MenuUi.field_27[1][var1].equals(Localization.field_115[128])) {
                     MenuUi.field_27[1][var1] = Localization.field_115[60];
                     break;
                  }
               }
            }

            this.method_65();
            this.method_60();
            this.method_69();
            break;
         case 54:
            if (!MenuUi.field_35 && this.field_219 > 1 && !this.method_84()) {
               break;
            }

            int var94 = field_173[9];
            if (this.field_143 == this.field_146) {
               var94 = 256;
            }

            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = MenuUi.field_35 ? 1 : 2;
               MenuUi.field_30 = MenuUi.method_16(119);
               field_173[3] = MenuUi.field_35 ? 4 : 11;
               this.field_143 = null;
               return;
            }

            if (MenuUi.method_13(var94)) {
               MessageScreens.field_66 = MenuUi.field_30;
               this.field_157 = 17;
               this.field_158 = -2;
               this.field_156 = 54;
               field_173[3] = 5;
            }

            return;
         case 55:
            if (!MenuUi.field_35 && this.field_219 > 1 && !this.method_84()) {
               break;
            }

            int var92 = field_173[9];
            if (this.field_143 == this.field_146) {
               var92 = 256;
            }

            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = 15;
               MenuUi.field_32 = true;
               MenuUi.field_30 = MenuUi.method_16(103);
               field_173[3] = 52;
               this.field_143 = null;
               return;
            }

            if (MenuUi.method_13(var92)) {
               field_173[42] = 0;
               this.repaint();
               this.serviceRepaints();
               if (field_164[27] != MenuUi.field_30) {
                  field_164[27] = (byte)MenuUi.field_30;
                  this.method_60();
                  this.repaint();
                  this.serviceRepaints();
                  this.field_123 = new Localization(this.field_279[MenuUi.field_30], false, this.field_247);
                  this.repaint();
                  this.serviceRepaints();
                  this.method_135();
                  this.repaint();
                  this.serviceRepaints();
                  int var4 = field_173[59];
                  var92 = field_173[60];
                  MenuUi.method_15(this.field_210, var4 - 2, var92);
                  Localization.field_116[4] = Localization.method_54(Localization.field_116[4], var4 - 44);
                  Localization.field_116[5] = Localization.method_54(Localization.field_116[5], var4 - 44);
                  if (this.field_123 != null && Localization.field_116 != null) {
                     this.field_125 = Localization.field_116[6];
                  }

                  this.field_125 = Localization.method_54(this.field_125, var92);
                  if (field_173[40] == 2) {
                     MenuUi.field_27[15][0] = Localization.field_115[104];
                  } else {
                     MenuUi.field_27[15][0] = Localization.field_115[105];
                  }

                  if (this.field_247) {
                     int var5 = MenuUi.field_29;
                     MenuUi.field_29 = 1;
                     int var2;
                     if ((var2 = MenuUi.method_16(128)) == -1) {
                        var2 = MenuUi.method_16(60);
                     }

                     if (field_164[26] == 1) {
                        MenuUi.field_27[1][var2] = Localization.field_115[128];
                     } else {
                        MenuUi.field_27[1][var2] = Localization.field_115[60];
                     }

                     MenuUi.field_29 = var5;
                  }

                  MenuUi.method_14();
               }

               MenuUi.field_29 = 15;
               MenuUi.field_32 = true;
               MenuUi.field_30 = MenuUi.method_16(103);
               field_173[3] = 52;
               this.field_143 = null;
               MenuUi.field_49 = 1;
            }

            return;
         case 56:
            if (this.field_143 == this.field_137) {
               MenuUi.field_32 = true;
               this.field_143 = null;
               MenuUi.field_29 = 1;
               MenuUi.field_30 = MenuUi.method_16(60);
               field_173[3] = 4;
            }

            if (this.field_286 != 0) {
               MenuUi.field_32 = true;
               if (this.field_286 == 1) {
                  field_164[25] = 0;
                  field_164[26] = 1;
                  this.method_102(this.field_285);
                  field_173[3] = 58;
               } else {
                  label1219: {
                     this.field_156 = 4;
                     this.field_158 = 60;
                     this.field_157 = 1;
                     field_173[3] = 5;
                     if (this.field_286 != -1) {
                        if (this.field_286 == -2) {
                           MessageScreens.field_66 = 18;
                           break label1219;
                        }

                        if (this.field_286 == -3) {
                           MessageScreens.field_66 = 16;
                           break label1219;
                        }

                        if (this.field_286 == -4) {
                           MessageScreens.field_66 = 15;
                           break label1219;
                        }
                     }

                     MessageScreens.field_66 = 17;
                  }
               }

               this.method_60();
               return;
            }
            break;
         case 57:
            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = 1;
               MenuUi.field_30 = MenuUi.method_16(60);
               field_173[3] = 4;
               this.field_143 = null;
               return;
            }
            break;
         case 58:
            if (this.field_143 == this.field_137) {
               MenuUi.field_29 = 1;
               int var3;
               if ((var3 = MenuUi.method_16(60)) == -1) {
                  var3 = 0;
               }

               MenuUi.field_30 = var3;
               MenuUi.method_14();
               field_173[3] = 4;
               this.field_143 = null;
               return;
            }
            break;
         case 59:
            if (this.field_143 == this.field_137) {
               this.method_69();
               return;
            }

            if (this.field_143 == this.field_146) {
               return;
            }
      }
   }

   // $VF: renamed from: s () void
   private void method_86() {
      field_119 = false;
      this.field_143 = null;
      MenuUi.field_29 = 5;
      MenuUi.field_30 = 0;
      field_173[3] = 22;
   }

   // $VF: renamed from: c () boolean
   private static boolean method_87() {
      boolean var0 = true;
      char[] var1 = NicknameEditor.field_60;

      for (int var2 = 6; var2 >= 0; var2--) {
         if (var1[var2] != ' ' && var1[var2] != 0) {
            var0 = false;
            break;
         }
      }

      return var0;
   }

   // $VF: renamed from: a (boolean) void
   private void method_88(boolean var1) {
      field_173[47] = method_58(field_164, 1 + (this.field_242 << 2), 4);

      try {
         String var2 = "/ms_bw.png";
         this.field_192 = Img.createImage("/ms_bw.png");
         var2 = "/ms_c.png";
         this.field_193 = Img.createImage("/ms_c.png");
      } catch (IOException var3) {
      }

      field_173[85] = Integer.MAX_VALUE;
      if (var1) {
         field_173[3] = 34;
         this.field_208 = 0;
         this.field_275 = true;
      } else {
         this.field_242 = 0;
         this.method_66(this.field_242);
      }
   }

   // $VF: renamed from: t () void
   private void method_89() {
      this.method_91(field_164, 28, NicknameEditor.field_60, 0);
      MenuUi.field_29 = 6;
      MenuUi.field_30 = -1;
      NicknameEditor.method_25();
      field_173[3] = 23;
   }

   // $VF: renamed from: u () void
   private void method_90() {
      MessageScreens.field_69 = 2048 * field_173[86] / Integer.parseInt(this.method_144("NAMCO-WAIT"));

      for (int var3 = 4; var3 >= this.field_219; var3--) {
         int var1;
         int var2 = (var1 = var3 * 7) + 7;
         System.arraycopy(field_128, 0, this.field_231, var1, field_128.length);
         var1 += field_128.length;

         while (var1 < var2) {
            this.field_231[var1++] = ' ';
         }
      }

      field_173[85] = 5000;
      this.field_175 = 50;
      this.field_176 = System.currentTimeMillis();
      this.field_220 = 0;
      this.method_95();
      field_173[3] = 33;
      field_173[98] = 0;
      field_173[49] = 0;
      if (this.field_223 != 0) {
         this.method_124();
         if (this.method_128()) {
            this.method_71();
         }
      }
   }

   // $VF: renamed from: a (byte[], int, char[], int) void
   private void method_91(byte[] var1, int var2, char[] var3, int var4) {
      for (int var5 = 6; var5 >= 0; var5--) {
         var3[var4 + var5] = (char)method_58(var1, var2 + (var5 << 1), 2);
      }
   }

   // $VF: renamed from: a (char[], int, byte[], int) void
   private void method_92(char[] var1, int var2, byte[] var3, int var4) {
      for (int var5 = 6; var5 >= 0; var5--) {
         method_59(var1[var2 + var5], var3, var4 + (var5 << 1), 2);
      }
   }

   // $VF: renamed from: a (int) int
   private int method_93(int var1) {
      if (var1 == this.field_220) {
         var1 = 4;
      } else if (var1 >= 4) {
         var1 = this.field_220;
      }

      return var1;
   }

   // $VF: renamed from: b (int) int
   private int method_94(int var1) {
      int var2 = -1;
      if (var1 == 4) {
         var2 = this.field_220;
      } else if (var1 == this.field_220) {
         if (this.field_219 > 4) {
            var2 = 4;
         }
      } else if (var1 < this.field_219) {
         var2 = var1;
      }

      return var2;
   }

   // $VF: renamed from: v () void
   private void method_95() {
      this.field_221 = this.method_93(this.field_222);

      for (int var1 = 3; var1 >= 0; var1--) {
         this.field_215[var1] = this.method_94(var1) == -1;
      }
   }

   // $VF: renamed from: a (int, int, int) void
   private void method_96(int var1, int var2, int var3) {
      int var4 = var1 * 71 / 100;
      int var5 = var2 - var4 - 1;
      int var6 = var3 - var4 - 1;
      int var11;
      int var7 = var11 = (var4 << 1) + 3;
      if (var5 < 0) {
         var11 += var5;
         var5 = 0;
      }

      if (var6 < 0) {
         var7 += var6;
         var6 = 0;
      }

      if (var5 + var11 >= field_152) {
         var11 = field_152 - var5;
      }

      if (var6 + var7 >= field_153) {
         var7 = field_153 - var6;
      }

      this.field_184.setClip(var5, var6, var11, var7);
      this.field_184.drawImage(this.field_185, 0, 0, 0);
      var4 = 2;
      var5 = var1 - 1;
      var6 = 1 - var1 + 1;
      int var15 = 3;
      var1 = var1 * -2 + 5 - 2;
      int var8 = var5 + 1;

      while (var4 < var5 - 2) {
         if (var6 < 0) {
            var6 += var15;
            var15 += 2;
            var1 += 2;
         } else {
            var6 += var1;
            var15 += 2;
            var1 += 4;
            var5--;
         }

         var4++;
         if (var5 != var8) {
            int var9 = (var4 << 1) + 1;
            this.field_184.setClip(-var4 + var2, var5 + var3, var9, 1);
            this.field_184.drawImage(this.field_185, 0, 0, 0);
            this.field_184.setClip(-var4 + var2, -var5 + var3, var9, 1);
            this.field_184.drawImage(this.field_185, 0, 0, 0);
            this.field_184.setClip(var5 + var2, -var4 + var3, 1, var9);
            this.field_184.drawImage(this.field_185, 0, 0, 0);
            this.field_184.setClip(-var5 + var2, -var4 + var3, 1, var9);
            this.field_184.drawImage(this.field_185, 0, 0, 0);
         }
      }
   }

   // $VF: renamed from: a (int, int, int, int) void
   private void method_97(int var1, int var2, int var3, int var4) {
      this.field_184.setClip(0, 0, field_152, field_153);
      this.field_184.setColor(var4);
      this.field_184.drawArc(var2 - var1, var3 - var1, var1 << 1, var1 << 1, 0, 360);
   }

   // $VF: renamed from: w () void
   private void method_98() {
      if (field_173[0] == 2) {
         this.method_111(this.field_184);
         this.method_110(this.field_183, 2);
         this.method_113(this.field_184);
         this.method_112(this.field_184, this.field_220);
         field_173[52] = 1;
      }

      if ((field_173[0] & 16) != 0) {
         this.method_111(this.field_184);
         this.method_112(this.field_184, this.field_220);
         this.method_113(this.field_184);
         field_173[52] = 1;
      }

      if (field_173[0] == 10) {
         this.method_111(this.field_184);
         this.method_113(this.field_184);
         this.method_110(this.field_183, 2);
         this.method_112(this.field_184, this.field_220);
         field_173[52] = 1;
      }

      if (field_173[0] == 4) {
         this.method_110(this.field_183, 1);
      }

      if (field_173[0] == 5) {
         this.method_110(this.field_183, 0);
      }

      this.field_186.setClip(0, 0, field_152, field_153);
      this.field_186.drawImage(this.field_191, 0, 0, 20);
      if (field_173[73] == 1) {
         int var2;
         Object var3;
         int var9;
         if (this.field_219 <= 1) {
            var3 = Localization.field_115[129];
            var2 = 0;
            var9 = Localization.field_115[129].length();
            this.field_186.setColor(this.field_219 <= 1 ? -16711681 : this.field_214[this.field_220]);
            BitmapFont.field_85.method_29(this.field_186, (String)var3, 0, var9, field_152 >> 1, field_155[5][0] * 10 - 1, 17);
         } else {
            var3 = this.field_231;
            var2 = var9 = this.field_220 * 7;
            var9 += 7;

            while (this.field_231[--var9] == ' ') {
            }

            var9++;
         }

         this.field_186.setColor(this.field_219 <= 1 ? -16711681 : this.field_214[this.field_220]);
         BitmapFont.field_85.method_29(this.field_186, var3, var2, var9 - var2, field_152 >> 1, field_155[5][0] * 10 - 1, 17);
      }

      if (field_173[72] == 1) {
         this.field_186.setColor(this.field_219 <= 1 ? -256 : this.field_214[this.field_220]);
         BitmapFont.field_85.method_31(this.field_186, Localization.field_115[130], field_152 >> 1, field_155[5][1] * 10 - 1, 17);
      }

      int var10002 = field_155[6][0];
      int var10003 = field_155[6][2];
      int var10004 = field_155[6][1];
      int var6 = field_155[6][3];
      int var5 = var10004;
      int var4 = var10003;
      int var24 = var10002;
      Gfx var16 = this.field_186;
      int var7;
      if (((int)System.currentTimeMillis() & 256) != 0 && field_173[27] >= 2 && field_173[3] == 10) {
         var7 = 0;
      } else {
         var7 = 2;
      }

      if ((this.field_180[var4 * (field_150 + 2) + var24] & 15) == 2) {
         this.method_105(var16, var7, var24 * 10, var4 * 10);
      }

      if ((this.field_180[var4 * (field_150 + 2) + var5] & 15) == 2) {
         this.method_105(var16, var7, var5 * 10, var4 * 10);
      }

      if ((this.field_180[var6 * (field_150 + 2) + var24] & 15) == 2) {
         this.method_105(var16, var7, var24 * 10, var6 * 10);
      }

      if ((this.field_180[var6 * (field_150 + 2) + var5] & 15) == 2) {
         this.method_105(var16, var7, var5 * 10, var6 * 10);
      }

      if (field_173[75] == 1) {
         var16 = this.field_186;
         var16.setColor(this.field_219 < 2 ? -65536 : this.field_214[this.field_220]);
         BitmapFont.field_85.method_31(var16, Localization.field_115[120], field_152 >> 1, field_155[5][1] * 10 - 1, 17);
      }

      if (field_173[88] == 1) {
         int var18 = 83 + this.field_212[0];
         this.method_105(this.field_186, var18, field_152 - field_149[(var18 << 2) + 2] >> 1, field_155[1][1] * 10 + field_148[2]);
      } else if (field_173[88] == 2) {
         this.method_105(this.field_186, 75 + this.field_212[0], field_152 - field_148[0] >> 1, field_155[1][1] * 10 + field_148[1]);
      }

      if (field_173[27] != 0) {
         for (int var19 = 0; var19 < 4; var19++) {
            int[] var25;
            (var25 = this.field_174[var19])[3] = var25[2];
            var25[4] = var25[1];
            this.field_266[var19] = (var25[3] * 10 >> 3) + field_148[5];
            this.field_267[var19] = (var25[4] * 10 >> 3) + field_148[6];
            if (this.field_174[var19][7] > 1) {
               this.method_105(this.field_186, 23 + var25[6], this.field_266[var19], this.field_267[var19]);
            }
         }

         if (field_173[13] != 0) {
            int var20 = field_173[30] + 91 - 1;
            this.method_105(
               this.field_186,
               var20,
               (field_173[18] * 10 >> 3) - (field_149[(var20 << 2) + 2] >> 1),
               (field_173[19] * 10 >> 3) - (field_149[(var20 << 2) + 3] >> 1)
            );
         } else {
            if (field_173[27] == 1) {
               field_173[21] = this.field_169[3][0];
            } else if (field_173[1] == 24 && field_173[3] == 10) {
               if (field_173[63] >= 3) {
                  if (field_173[61] == 0) {
                     field_173[61] = 2;
                  }
               } else if (field_173[6] % (field_173[87] > 3 ? 1 : 2) == 0) {
                  field_173[61] = field_173[61] + 1 & 3;
               }

               field_173[21] = this.field_169[field_173[20] + 3][field_173[61]];
            }

            field_173[18] = field_173[22];
            field_173[19] = field_173[17];
            this.method_105(this.field_186, 3 + field_173[21], (field_173[18] * 10 >> 3) + field_148[3], (field_173[19] * 10 >> 3) + field_148[4]);
         }

         for (int var21 = 0; var21 < 4; var21++) {
            int[] var26;
            if ((var26 = this.field_174[var21])[7] == 1) {
               this.method_105(this.field_186, 23 + var26[6], this.field_266[var21], this.field_267[var21]);
            }
         }
      }

      int var11;
      if ((var11 = field_173[1]) == 23) {
         var11 = field_173[15];
      }

      if (this.field_219 > 1 && (var11 == 24 || var11 >= 13 && var11 <= 19)) {
         for (int var22 = 3; var22 >= 0; var22--) {
            int[] var27 = this.field_174[var22];
            if ((var11 = field_173[1]) == 23) {
               var11 = field_173[15];
            }

            boolean var30 = var11 != 24;
            boolean showName = var27[7] == 2
               ? !var30
               : var27[7] == 1 && (this.field_215[var22] ? var30 && this.field_216 == var22 : !var30 || this.field_216 == var22);
            int var33;
            if (showName) {
               this.field_186.setColor(this.field_214[this.field_219 != 1 && var22 == this.field_220 ? 4 : var22]);
               var30 &= this.field_216 == var22;
               char[] var28;
               if (this.field_215[var22]) {
                  var28 = field_128;
                  var11 = 0;
                  var33 = field_128.length;
               } else {
                  var28 = this.field_231;
                  var11 = var22 * 7;
                  var33 = 6;

                  while (this.field_231[var11 + var33] == ' ') {
                     var33--;
                  }

                  var33++;
               }

               var7 = var33 * BitmapFont.field_85.method_33((byte)1) >> 1;
               var6 = this.field_266[var22] + 10;
               int var8 = 1;
               if (!var30) {
                  if (var6 + var7 >= field_152) {
                     var6 = field_152;
                     var8 = 8;
                  } else if (var6 - var7 < 0) {
                     var6 = 0;
                     var8 = 4;
                  }
               }

               var7 = this.field_267[var22] - 1;
               if (!var30 && var7 < BitmapFont.field_85.method_33((byte)1)) {
                  var7 += 21 + BitmapFont.field_85.method_33((byte)1);
               }

               BitmapFont.field_85.method_29(this.field_186, var28, var11, var33, var6, var7, var8 | 32);
               if (var30) {
                  var7 += 20 + BitmapFont.field_85.method_33((byte)1);
                  BitmapFont.field_85.method_31(this.field_186, this.field_217, var6, var7, 33);
                  BitmapFont.field_85.method_31(this.field_186, this.field_218, var6, ++var7, 17);
               }
            }
         }
      }

      if (this.field_220 != this.field_222 && field_173[1] == 24) {
         if ((var11 = field_154 - field_173[4] <= 15 ? field_173[95] - 32 : 255) < 0) {
            var11 = 0;
            this.field_184.setClip(0, 0, field_173[59], field_173[60]);
            this.field_184.drawImage(this.field_185, field_173[70], field_173[71], 0);
         }

         if (var11 != 0) {
            this.field_184.translate(field_173[70], field_173[71]);
            this.method_108(this.field_184, 1, 0, 0);
            if (field_173[88] != 0) {
               this.method_97(17, field_152 >> 1, field_155[1][1] * 10 + (field_148[0] >> 2), -256);
            }

            for (int var15 = 7; var15 >= 0; var15--) {
               int var23;
               var5 = (var23 = var15 & 3) == this.field_221 ? field_173[89] : field_173[90];
               var24 = this.field_174[var23][2] * 10 >> 3;
               var4 = this.field_174[var23][1] * 10 >> 3;
               if (var15 >= 4) {
                  this.method_97((var5 >> 1) + 2, var24, var4, this.field_214[this.field_219 != 1 && var23 == this.field_220 ? 4 : var23]);
               } else {
                  this.method_96(var5 >> 1, var24, var4);
               }
            }

            if (field_173[88] != 0) {
               this.method_96(15, field_152 >> 1, field_155[1][1] * 10 + (field_148[0] >> 2));
            }

            this.field_184.translate(-this.field_184.getTranslateX(), -this.field_184.getTranslateY());
         }
      } else {
         this.field_184.setClip(0, 0, field_173[59], field_173[60]);
         this.field_184.drawImage(this.field_185, field_173[70], field_173[71], 0);
      }

      this.method_103();
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx) void
   private void method_99(Gfx var1) {
      MenuUi.field_49 = 1;
      if (true) {
         var1.setClip(0, 0, 360, 640);
         var1.setColor(0);
         var1.fillRect(0, 0, 360, 640);
      }

      MenuUi.field_48 = 0;
      MenuUi.method_17(var1);
      MenuUi.method_19(var1);
      MenuUi.method_23(var1);
      int var2 = 640 - MenuUi.field_41.getHeight() - MenuUi.field_42.getHeight();
      int var3 = Localization.field_116[4].length;
      int var4 = BitmapFont.field_85.method_33((byte)1);
      var3 = var3 * var4 + (var3 - 1) * 6;
      var2 = (var2 - var3 - 20 - MenuUi.field_44.getHeight() - MenuUi.field_45.getHeight()) / 2;
      var3 = 360 - MenuUi.field_44.getWidth() >> 1;
      var4 = MenuUi.field_42.getHeight() + var2;
      var1.setClip(0, 0, 360, 640);
      var1.drawImage(MenuUi.field_44, var3, var4, 0);
      var4 = var4 + MenuUi.field_44.getHeight() + var2;
      var1.drawImage(MenuUi.field_45, 360 - MenuUi.field_45.getWidth() >> 1, var4, 0);
      var4 += MenuUi.field_45.getHeight();
      var4 += 10;
      if (field_173[3] == 3) {
         if ((field_173[6] & 127) < 65) {
            this.field_184.setColor(-65536);

            for (int var5 = 0; var5 < Localization.field_116[4].length; var5++) {
               BitmapFont.field_85
                  .method_31(this.field_184, Localization.field_116[4][var5], field_173[59] >> 1, var4 + var5 * (BitmapFont.field_85.method_33((byte)1) + 6), 17);
            }
         } else {
            this.field_184.setColor(-1);
            String var6 = Localization.field_115[81];
            BitmapFont.field_85.method_31(this.field_184, var6 + " " + field_173[47] * 10, field_173[59] >> 1, var4, 17);
         }
      }

      this.field_184.setColor(-65536);
      this.field_184.setColor(-1);
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, java.lang.String[]) void
   private static void method_100(Gfx var0, String[] var1) {
      var0.setClip(0, 0, 360, 640);
      int var2 = BitmapFont.field_85.method_33((byte)1);
      int var3 = 16 + (var2 + 6) * var1.length - 6;
      int var4 = 640 - var3 >> 1;
      var0.setClip(0, 0, 360, 640);
      var0.setColor(0);
      var0.fillRect(21, var4, 318, var3);
      var0.setColor(65751);
      var0.drawRect(21, var4, 317, var3 - 1);
      var0.setColor(65535);
      var4 += 8;

      for (int var5 = 0; var5 < var1.length; var5++) {
         BitmapFont.field_85.method_31(var0, var1[var5], field_173[59] >> 1, var4, 17);
         var4 += var2 + 6;
      }
   }

   // $VF: renamed from: a () void
   public final void method_101() {
      long var1;
      long var3 = var1 = System.currentTimeMillis();
      MenuUi.field_31 = 1;

      while (var3 - var1 < 550L) {
         if (MenuUi.field_47) {
            MenuUi.field_49 = 1;
         }

         this.repaint();
         this.serviceRepaints();
         if (((var3 = System.currentTimeMillis()) - var1) % 250L > 125L) {
            MenuUi.field_31 = 2;
         } else {
            MenuUi.field_31 = 1;
         }
      }

      MenuUi.field_31 = 0;
      this.method_115();
      MenuUi.field_49 = 2;
      this.field_272 = 9999;
      this.field_249 = false;
      field_173[8] = field_173[7] = field_173[9] = 0;
      field_120 = true;
      this.field_143 = null;
   }

   public final void paint(Gfx var1) {
      if (this.getWidth() != 360) {
         if (!this.field_268 && field_173[3] == 10) {
            this.keyPressed(-6);
         }

         if (field_173[3] != -2 && field_173[3] != -1 && field_173[3] != 2 && field_173[3] != 50 && field_173[3] != 1) {
            int var2 = this.getWidth();
            int var3 = this.getHeight();
            var1.setClip(0, 0, var2, var3);
            var1.setColor(0);
            var1.fillRect(0, 0, var2, var3);
            var1.setColor(16777215);
            int var4 = BitmapFont.field_85.method_33((byte)1) + 6;
            int var5 = this.field_125.length;
            int var6 = var3 - var4 * var5 >> 1;

            for (int var7 = 0; var7 < var5; var7++) {
               BitmapFont.field_85.method_31(var1, this.field_125[var7], var2 >> 1, var6 + var7 * var4, 17);
            }

            this.field_268 = true;
            return;
         }

         this.field_268 = true;
      } else if (this.field_268) {
         this.field_268 = false;
         MenuUi.field_49 = 2;
      }

      if (field_173[3] != -2) {
         var1.translate(-var1.getTranslateX(), -var1.getTranslateY());
         var1.setClip(0, 0, this.getWidth(), this.getHeight());
         var1.setFont(this.field_210);
         this.field_184 = var1;
         this.field_257 = 0;
         int var47 = field_173[3];
         if (var47 != this.field_281) {
            this.field_281 = var47;
            switch (var47) {
               case -1:
                  this.method_122(this.field_142, this.field_139);
                  break;
               case 0:
                  this.method_122(this.field_147, this.field_142);
                  break;
               case 1:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 2:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 3:
                  this.method_122(this.field_140, this.field_139);
                  break;
               case 4:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 5:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 6:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 7:
                  this.method_122(this.field_142, this.field_141);
                  break;
               case 8:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 9:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 10:
                  this.method_122(this.field_138, this.field_142);
                  break;
               case 11:
                  if (this.field_219 > 1 && !field_121) {
                     this.method_122(this.field_146, this.field_142);
                  } else {
                     this.method_122(this.field_146, this.field_137);
                  }
                  break;
               case 12:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 13:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 14:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 15:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 16:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 17:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 18:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 19:
                  this.method_122(this.field_142, this.field_142);
               case 20:
               case 21:
               case 39:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               default:
                  break;
               case 22:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 23:
                  if (method_87()) {
                     this.method_122(this.field_142, this.field_137);
                  } else {
                     this.method_122(this.field_147, this.field_137);
                  }
                  break;
               case 24:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 25:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 26:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 27:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 28:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 29:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 30:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 31:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 32:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 33:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 34:
                  if (this.field_223 != 0) {
                     this.method_122(this.field_142, this.field_137);
                     break;
                  }
               case 55:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 35:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 36:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 37:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 38:
                  this.method_122(this.field_146, this.field_142);
                  break;
               case 50:
                  this.method_122(this.field_142, this.field_142);
                  break;
               case 51:
                  this.method_122(this.field_146, this.field_139);
                  break;
               case 52:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 53:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 54:
                  this.method_122(this.field_146, this.field_137);
                  break;
               case 56:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 57:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 58:
                  this.method_122(this.field_142, this.field_137);
                  break;
               case 59:
                  this.method_122(this.field_142, this.field_137);
            }
         }

         switch (field_173[3]) {
            case -1:
               var1.setClip(0, 0, 360, 640);
               var1.setColor(-16777216);
               var1.fillRect(0, 0, field_173[59], field_173[60]);
               var1.setColor(-1);
               int var46 = field_173[60] - ((field_173[92] << 1) + 12) >> 1;
               var1.drawString(this.field_181, field_173[59] >> 1, var46, 17);
               return;
            case 0:
               MenuUi.method_24(var1, this.field_210);
               return;
            case 1:
               Gfx var45 = this.field_184;
               var47 = field_173[42] * 251 / 100;
               var45.setClip(0, 0, 360, 640);
               var45.setColor(-16777216);
               var45.fillRect(0, 0, 360, 640);
               var45.setColor(-1);
               var45.drawRect(53, 300, 254, 40);
               var45.fillRect(57, 304, var47, 35);
               break;
            case 2:
               Gfx var69 = this.field_184;
               if (this.field_194.length == 0) {
                  boolean var112 = false;
               } else if (this.field_201 == this.field_194.length - 1 && this.field_202 == 2 && this.field_204 > this.field_195[this.field_201]) {
                  boolean var111 = false;
               } else {
                  int var18 = this.field_197[this.field_201];
                  int var98 = 0;
                  int var103 = 0;
                  var98 = this.getWidth();
                  var103 = this.getHeight();
                  var69.setClip(0, 0, var98, var103);
                  var69.setColor(var18);
                  var69.fillRect(0, 0, var98, var103);
                  Img var107 = this.field_200[this.field_201];
                  int var110 = (var98 - var107.getWidth() >> 1) + this.field_198[this.field_201 << 1];
                  var103 = (var103 - var107.getHeight() >> 1) + this.field_198[(this.field_201 << 1) + 1];
                  var69.drawImage(var107, var110, var103, 0);
                  boolean var10000 = true;
               }

               int var90 = 0;
               int var100 = 0;
               var90 = this.getWidth();
               var100 = this.getHeight();
               this.method_62(0, 0, var90, var100, 2, 0);
               break;
            case 3:
               this.method_99(this.field_184);
               if (field_173[3] == 3) {
                  this.method_62(0, 0, field_173[59], 640 - MenuUi.field_41.getHeight() - 10, 2, 0);
               }
               break;
            case 4:
            case 6:
            case 11:
            case 14:
            case 16:
            case 18:
            case 22:
            case 24:
            case 27:
            case 28:
            case 30:
            case 38:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
               if (field_173[3] == 52) {
                  if (MenuUi.field_28[15] == null) {
                     MenuUi.field_28[15] = new boolean[5];
                  }

                  if (MenuUi.field_35) {
                     MenuUi.field_28[15][MenuUi.method_16(59)] = false;
                     MenuUi.field_28[15][MenuUi.method_16(102)] = false;
                  } else {
                     MenuUi.field_28[15][MenuUi.method_16(59)] = true;
                     MenuUi.field_28[15][MenuUi.method_16(102)] = true;
                  }
               }

               MenuUi.method_24(this.field_184, this.field_210);
               break;
            case 5:
            case 7:
            case 17:
            case 25:
            case 26:
            case 29:
            case 31:
            case 32:
            case 57:
               MessageScreens.method_28(this.field_184);
            case 8:
            case 9:
            case 15:
            case 20:
            case 21:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            default:
               break;
            case 10:
               this.method_98();
               int var89 = field_173[60] + field_173[92] - BitmapFont.field_85.method_33((byte)1) - 10;
               int var97 = BitmapFont.field_85.method_33((byte)1) + 20;
               if (((Command)this.field_278.firstElement()).getLabel() != " ") {
                  this.method_62(0, var89, 160, var97, 1, 0);
               }

               if (((Command)this.field_278.lastElement()).getLabel() != " ") {
                  this.method_62(field_173[59] - 160, var89, 160, var97, 1, 1);
               }

               if (field_173[3] == 11) {
               }
               break;
            case 12:
            case 34:
               this.field_184.setColor(0);
               this.field_184.fillRect(0, 0, field_173[59], this.getHeight());
               this.field_184.setColor(MessageScreens.field_71);
               int var43 = field_173[60] - (BitmapFont.field_85.method_33((byte)1) + 18 + this.field_193.getHeight()) >> 1;
               MenuUi.field_48 = 0;
               MenuUi.method_17(var1);
               MenuUi.method_19(var1);
               MenuUi.method_23(var1);
               var43 += BitmapFont.field_85.method_33((byte)1) + 18;
               var47 = field_173[59] - this.field_193.getWidth() >> 1;
               this.field_184.drawImage(this.field_192, var47, var43, 20);
               int var96 = 0;
               int var88 = 0;
               int var76 = (this.field_193.getWidth() - 4 - 24) / 2;
               if (this.field_242 > 1) {
                  var96 = var76 + 4 + 12;
               }

               if ((this.field_242 & 1) != 0) {
                  var88 = var76 + 4 + 12;
               }

               this.field_184.drawRegion(this.field_193, var88, var96, var76 + 12, var76 + 12, 0, var47 + var88, var43 + var96, 20);
               if (field_173[85] <= 2000 && (field_173[6] & 7) < 4) {
                  var1.setColor(16711680);
                  var1.fillRect(var47 + var88, var43 + var96, var76 + 6, 6);
                  var1.fillRect(var47 + var88, var43 + var96 + var76 + 6, var76 + 6, 6);
                  var1.fillRect(var47 + var88, var43 + var96, 6, var76 + 6);
                  var1.fillRect(var47 + var88 + var76 + 6, var43 + var96, 6, var76 + 6 + 6);
               }

               int var17 = var76 + 12 + 4;
               var76 = var76 + 12 + 4;
               this.method_62(var47, var43, var17, var76, 3, 0);
               this.method_62(var47 + var17, var43, var17, var76, 3, 1);
               this.method_62(var47, var43 + var76, var17, var76, 3, 2);
               this.method_62(var47 + var17, var43 + var76, var17, var76, 3, 3);
               if (this.field_223 != 0) {
                  method_100(this.field_184, Localization.field_116[5]);
               }
               break;
            case 13:
               if (field_173[0] == 2) {
                  this.method_111(this.field_184);
                  this.field_183.setColor(-16777216);
                  this.field_183.fillRect(0, 0, field_152, field_153);
                  this.method_113(this.field_184);
                  field_173[52] = 1;
                  field_173[0] = 0;
               }

               this.field_183.translate(field_173[68] - this.field_183.getTranslateX(), field_173[69] - this.field_183.getTranslateY());
               this.field_183.setColor(-16777216);
               this.field_183.fillRect(0, field_153 / 2 - 16, field_152, 32);
               this.field_183.fillRect(0, field_153 / 2 - 64, field_152, 128);
               label330:
               switch (field_173[76]) {
                  case 1:
                     switch (field_173[77]) {
                        case 0:
                           field_173[78] = field_152 << 4;
                           field_173[79] = field_153 / 2;
                           field_173[80] = 0;
                           field_173[81] = field_152 + 100 << 4;
                           field_173[82] = field_153 / 2;
                           field_173[83] = 8;
                           field_173[77]++;
                        case 1:
                           this.method_106(this.field_183, field_173[80], field_173[78] >> 4, field_173[79]);
                           this.method_106(this.field_183, field_173[83], field_173[81] >> 4, field_173[82]);
                           field_173[78] = field_173[78] - 126;
                           field_173[81] = field_173[81] - 156;
                           field_173[80]++;
                           if (field_173[80] == 4) {
                              field_173[80] = 0;
                           }

                           field_173[83]++;
                           if (field_173[83] == 10) {
                              field_173[83] = 8;
                           }

                           if (field_173[81] < -256) {
                              field_173[77]++;
                           }
                           break label330;
                        case 2:
                           field_173[78] = -3904;
                           field_173[79] = field_153 / 2 - 16;
                           field_173[80] = 4;
                           field_173[81] = -1792;
                           field_173[82] = field_153 / 2;
                           field_173[83] = 10;
                           field_173[77]++;
                        case 3:
                           this.method_106(this.field_183, field_173[83], field_173[81] >> 4, field_173[82]);
                           this.method_106(this.field_183, field_173[80], field_173[78] >> 4, field_173[79]);
                           field_173[78] = field_173[78] + 160;
                           field_173[81] = field_173[81] + 136;
                           field_173[80]++;
                           if (field_173[80] == 8) {
                              field_173[80] = 4;
                           }

                           field_173[83]++;
                           if (field_173[83] == 12) {
                              field_173[83] = 10;
                           }
                        default:
                           break label330;
                     }
                  case 2:
                     switch (field_173[77]) {
                        case 0:
                           field_173[78] = field_152 << 4;
                           field_173[79] = field_153 / 2;
                           field_173[80] = 0;
                           field_173[81] = field_152 + (field_173[59] >> 1) << 4;
                           field_173[82] = field_153 / 2;
                           field_173[83] = 8;
                           field_173[77]++;
                        case 1:
                           this.method_106(this.field_183, 18, (field_173[59] >> 1) + 16, field_153 / 2 + 6);
                           this.method_106(this.field_183, field_173[80], field_173[78] >> 4, field_173[79]);
                           this.method_106(this.field_183, field_173[83], field_173[81] >> 4, field_173[82]);
                           field_173[78] = field_173[78] - 126;
                           field_173[81] = field_173[81] - 156;
                           field_173[80]++;
                           if (field_173[80] == 4) {
                              field_173[80] = 0;
                           }

                           field_173[83]++;
                           if (field_173[83] == 10) {
                              field_173[83] = 8;
                           }

                           if (field_173[81] >> 4 <= (field_173[59] >> 1) + 16) {
                              field_173[81] = (field_173[59] >> 1) + 16 << 4;
                              field_173[77]++;
                              field_173[84] = 6;
                           }
                           break label330;
                        case 2:
                           this.method_106(this.field_183, field_173[80], field_173[78] >> 4, field_173[79]);
                           this.method_106(this.field_183, field_173[84] / 6 + 18, (field_173[59] >> 1) + 16, field_153 / 2 + 6);
                           this.method_106(this.field_183, field_173[83], field_173[81] >> 4, field_173[82]);
                           field_173[78] = field_173[78] - 126;
                           field_173[81] = field_173[81] - 12;
                           field_173[80]++;
                           if (field_173[80] == 4) {
                              field_173[80] = 0;
                           }

                           if ((field_173[84] & 1) == 0) {
                              field_173[83]++;
                           }

                           if (field_173[83] == 10) {
                              field_173[83] = 8;
                           }

                           field_173[84]++;
                           if (field_173[84] == 23) {
                              field_173[77]++;
                              field_173[84] = 0;
                           }
                           break label330;
                        case 3:
                           this.method_106(this.field_183, 22, (field_173[59] >> 1) + 16, field_153 / 2 + 6);
                           this.method_106(this.field_183, field_173[83], field_173[81] >> 4, field_173[82]);
                           field_173[84]++;
                           if (field_173[84] == 16) {
                              field_173[77]++;
                              field_173[84] = 0;
                           }
                           break label330;
                        case 4:
                           this.method_106(this.field_183, 23, (field_173[59] >> 1) + 16, field_153 / 2 + 6);
                           this.method_106(this.field_183, 12, field_173[81] >> 4, field_173[82]);
                           field_173[84]++;
                           if (field_173[84] == 13) {
                              field_173[77]++;
                              field_173[84] = 0;
                           }
                           break label330;
                        case 5:
                           this.method_106(this.field_183, 23, (field_173[59] >> 1) + 16, field_153 / 2 + 6);
                           this.method_106(this.field_183, 13, field_173[81] >> 4, field_173[82]);
                        default:
                           break label330;
                     }
                  case 3:
                     switch (field_173[77]) {
                        case 0:
                           field_173[78] = field_152 << 4;
                           field_173[79] = field_153 / 2;
                           field_173[80] = 0;
                           field_173[81] = field_152 + 100 << 4;
                           field_173[82] = field_153 / 2;
                           field_173[83] = 8;
                           field_173[77]++;
                        case 1:
                           this.method_106(this.field_183, field_173[80], field_173[78] >> 4, field_173[79]);
                           this.method_106(this.field_183, field_173[83], field_173[81] >> 4, field_173[82]);
                           field_173[78] = field_173[78] - 156;
                           field_173[81] = field_173[81] - 156;
                           field_173[80]++;
                           if (field_173[80] == 4) {
                              field_173[80] = 0;
                           }

                           field_173[83]++;
                           if (field_173[83] == 16) {
                              field_173[83] = 14;
                           }

                           if (field_173[81] < -256) {
                              field_173[77]++;
                           }
                           break;
                        case 2:
                           field_173[81] = -1792;
                           field_173[82] = field_153 / 2;
                           field_173[83] = 10;
                           field_173[77]++;
                           field_173[84] = 0;
                        case 3:
                           this.method_106(this.field_183, field_173[83], field_173[81] >> 4, field_173[82]);
                           if ((field_173[84] & 1) == 0) {
                              field_173[81] = field_173[81] + 220;
                           } else {
                              field_173[81] = field_173[81] + 92;
                           }

                           field_173[83]++;
                           if (field_173[83] == 18) {
                              field_173[83] = 16;
                           }

                           field_173[84]++;
                     }
               }

               this.field_184.drawImage(this.field_191, field_173[70], field_173[71], 20);
               this.field_183.translate(-field_173[68], -field_173[69]);
               this.method_103();
               break;
            case 19:
               this.field_184.setColor(-16777216);
               this.field_184.fillRect(0, 0, field_173[59], field_173[60]);
               this.field_184.setColor(-1);
               int var41 = field_173[60] - ((field_173[92] << 1) + 12) >> 1;
               BitmapFont.field_85.method_31(this.field_184, this.field_181, field_173[59] >> 1, var41, 17);
               var41 += field_173[92] + 12;
               BitmapFont.field_85.method_31(this.field_184, this.field_182, field_173[59] >> 1, var41, 17);
               break;
            case 23:
               NicknameEditor.method_24(this.field_184, this.field_210);
               break;
            case 33:
               this.field_184.setColor(0);
               this.field_184.fillRect(0, 0, field_173[59], this.getHeight());
               this.field_184.setColor(MessageScreens.field_71);
               int var36 = field_173[60] - (BitmapFont.field_85.method_33((byte)1) * MessageScreens.field_63[7].length + 12 + 24 + 12 + 216 + 12) >> 1;

               for (int var66 = 0; var66 < MessageScreens.field_63[7].length; var66++) {
                  BitmapFont.field_85.method_31(this.field_184, MessageScreens.field_63[7][var66], field_173[59] >> 1, var36, 17);
                  var36 += BitmapFont.field_85.method_33((byte)1);
               }

               this.field_184.setColor(MessageScreens.field_70);
               var36 += 12;
               BitmapFont.field_85.method_31(this.field_184, Localization.field_115[34], field_173[59] >> 1, var36, 17);
               var36 += 24;
               int var87 = field_173[59] - (174 + field_162[26]) >> 1;

               for (int var67 = 0; var67 < 5; var67++) {
                  if (var67 != this.field_220) {
                     this.field_184.setColor(this.field_214[var67]);
                     var1.fillRect(var87, var36, field_162[26], field_162[27]);
                     this.method_107(this.field_184, 6 + field_173[83], var87, var36);
                     this.field_184.setColor(var67 == this.field_222 ? method_104(this.field_214[var67]) : this.field_214[var67]);
                     BitmapFont.field_85.method_30(this.field_184, (byte)4, this.field_231, var67 * 7, 7, var87 + 6 + field_162[26], var36, 20);
                     var36 += 36;
                  }
               }

               var36 += 6;
               this.field_184.setColor(MessageScreens.field_70);
               BitmapFont.field_85.method_31(this.field_184, Localization.field_115[35], field_173[59] >> 1, var36, 17);
               var36 += 18;
               this.field_184.setColor(-256);
               this.method_107(this.field_184, 8 + (field_173[80] > 2 ? 1 : field_173[80]), var87, var36 + 1);
               BitmapFont.field_85.method_30(this.field_184, (byte)4, this.field_231, this.field_220 * 7, 7, var87 + 6 + field_162[26], var36, 20);
               break;
            case 35:
               this.field_184.setClip(0, 0, 360, 640);
               this.field_184.setColor(0);
               this.field_184.fillRect(0, 0, field_173[59], this.getHeight());
               int var32 = field_173[60] - ((BitmapFont.field_85.method_33((byte)1) << 1) + 12 + field_162[51] + 32 + 16 + 144) >> 1;
               var47 = (this.field_220 + 1) * 7;

               while (this.field_231[--var47] == ' ') {
               }

               var47 -= this.field_220 * 7 - 1;
               if (Localization.field_115[36].indexOf("%NAME%") != -1) {
                  int var95 = Localization.field_115[36].indexOf("%NAME%");
                  String var74 = Localization.field_115[36];
                  var74 = var74.substring(0, var95) + new String(this.field_231, this.field_220 * 7, var47) + var74.substring(var95 + 6);
                  int var83 = field_173[59] - BitmapFont.field_85.method_34(var74, (byte)1) >> 1;
                  this.field_184.setColor(this.field_214[this.field_220]);
                  BitmapFont.field_85.method_31(this.field_184, var74, var83, var32, 20);
               } else {
                  int var84 = field_173[59]
                        - (
                           BitmapFont.field_85.method_34(new String(this.field_231, this.field_220 * 7, var47), (byte)1)
                              + BitmapFont.field_85.method_34(Localization.field_115[36], (byte)1)
                        )
                     >> 1;
                  this.field_184.setColor(this.field_214[this.field_220]);
                  BitmapFont.field_85.method_29(this.field_184, this.field_231, this.field_220 * 7, var47, var84, var32, 20);
                  var84 += BitmapFont.field_85.method_34(new String(this.field_231, this.field_220 * 7, var47), (byte)1);
                  BitmapFont.field_85.method_31(this.field_184, Localization.field_115[36], var84, var32, 20);
               }

               var32 += BitmapFont.field_85.method_33((byte)1) + 12;
               int var86 = field_173[59] - (174 + field_162[26]) >> 1;
               this.method_107(this.field_184, 12 + (field_173[80] > 2 ? 1 : field_173[80]), var86, var32);
               this.field_184.setColor(-256);
               BitmapFont.field_85.method_30(this.field_184, (byte)4, this.field_231, this.field_220 * 7, 7, var86 + 6 + field_162[50], var32 - 1, 20);
               this.field_184.setColor(MessageScreens.field_70);
               var32 += field_162[51] + 32;
               BitmapFont.field_85.method_31(this.field_184, Localization.field_115[37], field_173[59] >> 1, var32, 17);
               var32 += BitmapFont.field_85.method_33((byte)1) + 16;

               for (int var65 = 0; var65 < 5; var65++) {
                  if (var65 != this.field_220) {
                     this.field_184.setClip(0, 0, 360, 640);
                     this.field_184.setColor(this.field_214[var65]);
                     var1.fillRect(var86, var32, field_162[26], field_162[27]);
                     this.method_107(this.field_184, 6 + field_173[83], var86, var32);
                     this.field_184.setColor(var65 == this.field_222 ? method_104(this.field_214[var65]) : this.field_214[var65]);
                     BitmapFont.field_85.method_30(this.field_184, (byte)4, this.field_231, var65 * 7, 7, var86 + 6 + field_162[26], var32, 20);
                     var32 += 36;
                  }
               }
               break;
            case 36:
               this.field_184.setColor(0);
               this.field_184.fillRect(0, 0, field_173[59], this.getHeight());
               this.field_184.setColor(MessageScreens.field_70);
               int var55;
               if (this.field_220 == this.field_222) {
                  var55 = 27;
               } else {
                  var55 = 28;
               }

               int var28 = field_173[60] - (BitmapFont.field_85.method_33((byte)1) * 3 + 20 + 20 + 10 + field_162[(var55 << 2) + 3]) >> 1;
               BitmapFont.field_85.method_31(this.field_184, Localization.field_115[40], field_173[59] >> 1, var28, 17);
               var28 += BitmapFont.field_85.method_33((byte)1) + 20;
               if (var55 != 27) {
                  this.field_184.setColor(this.field_214[this.field_222]);
                  this.field_184.fillRect(field_173[59] - field_162[114] >> 1, var28, field_162[114], field_162[115]);
               }

               this.method_107(this.field_184, var55, field_173[59] - field_162[(var55 << 2) + 2] >> 1, var28);
               var28 += field_162[(var55 << 2) + 3] + 20;
               this.field_184.setColor(MessageScreens.field_70);
               BitmapFont.field_85.method_31(this.field_184, this.field_123.field_113[var55 == 27 ? 5 : this.field_222], field_173[59] >> 1, var28, 17);
               var28 += BitmapFont.field_85.method_33((byte)1) + 10;
               String var79;
               if (var55 != 27) {
                  if (field_173[59]
                     < BitmapFont.field_85.method_34(Localization.field_115[47], (byte)1)
                        + 20
                        + field_162[38]
                        + BitmapFont.field_85.method_34(Localization.field_115[48], (byte)1)) {
                     var55 = field_173[59] - (BitmapFont.field_85.method_34(Localization.field_115[47], (byte)1) + 20 + field_162[38]) >> 1;
                     BitmapFont.field_85.method_31(this.field_184, Localization.field_115[47], var55, var28, 20);
                     var55 += BitmapFont.field_85.method_34(Localization.field_115[47], (byte)1) + 10;
                     this.method_107(this.field_184, 9, var55, var28 + (BitmapFont.field_85.method_33((byte)1) - field_162[39] >> 1));
                     var55 = (field_173[59] - BitmapFont.field_85.method_34(Localization.field_115[48], (byte)1)) / 2;
                     var28 += BitmapFont.field_85.method_33((byte)1) + 10;
                     var79 = Localization.field_115[48];
                  } else {
                     var55 = field_173[59]
                           - (
                              BitmapFont.field_85.method_34(Localization.field_115[47], (byte)1)
                                 + 20
                                 + field_162[38]
                                 + BitmapFont.field_85.method_34(Localization.field_115[48], (byte)1)
                           )
                        >> 1;
                     BitmapFont.field_85.method_31(this.field_184, Localization.field_115[47], var55, var28, 20);
                     var55 += BitmapFont.field_85.method_34(Localization.field_115[47], (byte)1) + 10;
                     this.method_107(this.field_184, 9, var55, var28 + (BitmapFont.field_85.method_33((byte)1) - field_162[39] >> 1));
                     var55 += field_162[38] + 10;
                     var79 = Localization.field_115[48];
                  }
               } else {
                  var55 = field_173[59]
                        - (
                           BitmapFont.field_85.method_34(Localization.field_115[49], (byte)1)
                              + 20
                              + field_162[118]
                              + BitmapFont.field_85.method_34(Localization.field_115[50], (byte)1)
                        )
                     >> 1;
                  BitmapFont.field_85.method_31(this.field_184, Localization.field_115[49], var55, var28, 20);
                  var55 += BitmapFont.field_85.method_34(Localization.field_115[49], (byte)1) + 10;
                  this.method_107(this.field_184, 29, var55, var28 + (BitmapFont.field_85.method_33((byte)1) - field_162[119] >> 1));
                  var55 += field_162[118] + 10;
                  var79 = Localization.field_115[50];
               }

               BitmapFont.field_85.method_31(this.field_184, var79, var55, var28, 20);
               break;
            case 37:
               this.field_184.setColor(0);
               this.field_184.fillRect(0, 0, field_173[59], this.getHeight());
               this.field_184.setColor(MessageScreens.field_71);
               int var20 = field_173[60]
                     - (36 + field_162[67] + field_162[63] + 6 + 24 + 6 + 16 + (this.field_219 << 4) + 16 + BitmapFont.field_85.method_33((byte)1))
                  >> 1;
               this.field_184.setColor(-65536);
               BitmapFont.field_85.method_31(this.field_184, Localization.field_115[30], field_173[59] >> 1, var20, 17);
               var20 += 24;
               this.method_107(this.field_184, 15, field_173[59] - field_162[62] >> 1, var20 + field_162[67]);
               this.field_184.setColor(this.field_214[field_173[10] & 7]);
               var47 = field_173[59] - field_162[66] >> 1;
               this.field_184.fillRect(var47, var20, field_162[66], field_162[67]);
               this.method_107(this.field_184, 16, var47, var20);
               var20 += 23;
               this.field_184.setColor(this.field_214[field_173[10] >> 3 & 7]);
               var47 -= 6 + field_162[66];
               this.field_184.fillRect(var47, var20, field_162[66], field_162[67]);
               this.method_107(this.field_184, 16, var47, var20);
               var20 += 2;
               var47 += 6 + field_162[66] << 1;
               if (this.field_219 > 2) {
                  this.field_184.setColor(this.field_214[field_173[10] >> 6 & 7]);
                  this.field_184.fillRect(var47, var20, field_162[66], field_162[67]);
                  this.method_107(this.field_184, 16, var47, var20);
               }

               var20 += field_162[63] + field_162[67] + 6 - 24 - 2;
               this.field_184.setColor(this.field_214[field_173[10] & 7]);
               var47 = ((field_173[10] & 7) + 1) * 7;

               while (this.field_231[--var47] == ' ') {
               }

               var47 -= (field_173[10] & 7) * 7 - 1;
               BitmapFont.field_85.method_29(this.field_184, this.field_231, (field_173[10] & 7) * 7, var47, field_173[59] >> 1, var20, 17);
               this.field_184.setColor(MessageScreens.field_70);
               var20 += 30;
               BitmapFont.field_85.method_31(this.field_184, Localization.field_115[31], field_173[59] >> 1, var20, 17);
               var20 += 28;

               for (int var54 = 0; var54 < this.field_219; var54++) {
                  this.field_184.setColor(this.field_214[field_173[10] >> var54 * 3 & 7]);
                  BitmapFont.field_85.method_29(this.field_184, this.field_231, (field_173[10] >> var54 * 3 & 7) * 7, 7, field_173[59] - 180 >> 1, var20, 20);
                  method_109(this.field_184, method_58(this.field_245[field_173[10] >> var54 * 3 & 7], 6, 3) * 10, (field_173[59] + 180 >> 1) - 7, var20);
                  var20 += 16;
               }

               var20 += 16;
               this.field_184.setColor(-65536);
               if ((field_173[6] & 63) < 50) {
                  for (int var16 = 0; var16 < Localization.field_116[4].length; var16++) {
                     BitmapFont.field_85
                        .method_31(this.field_184, Localization.field_116[4][var16], field_173[59] >> 1, var20 + var16 * BitmapFont.field_85.method_33((byte)1), 17);
                  }
               }

               this.method_62(0, 0, 360, 640, 4, -5);
               break;
            case 50:
               int var81 = 0;
               int var93 = 0;
               var81 = this.getWidth();
               var93 = this.getHeight();
               this.field_184.setColor(-16777216);
               this.field_184.setClip(0, 0, var81, var93);
               this.field_184.fillRect(0, 0, var81, var93);
               if (this.field_206[this.field_207] != null) {
                  int var73 = var81 - this.field_206[this.field_207].getWidth() >> 1;
                  int var15 = var93 - (this.field_206[this.field_207].getHeight() + 76) >> 1;
                  this.field_184.setColor(-1);
                  this.field_184.fillRect(0, var15 + 140, field_173[59], 38);
                  this.field_184.drawImage(this.field_206[this.field_207], var73, var15, 20);
                  if (System.currentTimeMillis() - this.field_264 > 90L || this.field_264 == 0L) {
                     this.field_207 = (this.field_207 + 1) % 4;
                     this.field_264 = System.currentTimeMillis();
                  }

                  this.method_62(0, 0, var81, var93, 2, 0);
               }
               break;
            case 51:
               MenuUi.method_24(var1, this.field_210);
               return;
            case 58:
               Gfx var48 = this.field_184;
               GameScreen var19 = this;
               MenuUi.field_49 = 1;
               var48.setColor(0);
               var48.setClip(0, 0, field_173[59], field_173[60]);
               var48.fillRect(0, 0, field_173[59], field_173[60]);
               MenuUi.field_48 = 0;
               MenuUi.method_17(var48);
               MenuUi.method_18(var48, new String[]{Localization.field_115[82]});
               if (this.field_271.size() >= 2) {
                  int var72 = MenuUi.field_48 + 20 + -4;
                  String var78 = (String)this.field_271.elementAt(0);
                  int var80 = Integer.parseInt((String)this.field_271.elementAt(1));
                  int var92 = 0;

                  for (int var9 = 0; var9 < var80; var9++) {
                     String var10 = (String)var19.field_271.elementAt(2 + var9 * 3) + ". ";
                     int var8;
                     if ((var8 = BitmapFont.field_85.method_34(var10, (byte)1)) > var92) {
                        var92 = var8;
                     }
                  }

                  for (int var106 = 0; var106 < var80; var106++) {
                     String var108;
                     if ((var108 = (String)var19.field_271.elementAt(2 + var106 * 3)).equals(var78)) {
                        var48.setColor(16711680);
                     } else {
                        var48.setColor(MenuUi.field_24);
                     }

                     var108 = var108 + ". ";
                     String var102 = (String)var19.field_271.elementAt(2 + var106 * 3 + 1);
                     String var11 = (String)var19.field_271.elementAt(2 + var106 * 3 + 2);
                     int var12 = var92 + 30 - BitmapFont.field_85.method_34(var108, (byte)1);
                     BitmapFont.field_85.method_31(var48, var108, var12, var72, 0);
                     int var13 = var92 + 30;
                     BitmapFont.field_85.method_31(var48, var102, var13, var72, 0);
                     int var14 = field_173[59] - 8 - 22 + 1 - BitmapFont.field_85.method_34(var11, (byte)1);
                     BitmapFont.field_85.method_31(var48, var11, var14, var72, 0);
                     var72 += BitmapFont.field_85.method_33((byte)1) + 6;
                  }

                  MenuUi.method_19(var48);
                  MenuUi.method_23(var48);
               }
               break;
            case 59:
               MenuUi.method_24(this.field_184, this.field_210);
               this.method_123();
               if (this.method_128()) {
                  this.method_71();
               } else if (this.field_225 == Integer.MAX_VALUE) {
                  field_173[3] = this.field_269;
                  MenuUi.field_29 = this.field_270;
                  if (field_173[3] == 10) {
                     this.field_129.resume();
                     field_173[0] = 2;
                  }

                  return;
               }
         }

         if (field_173[3] != 0 && field_173[3] != -1 && field_173[3] != 1 && field_173[3] != 2 && field_173[3] != 50) {
            field_173[0] = 0;
         }

         if (this.field_219 > 1 && this.field_225 != Integer.MAX_VALUE && field_173[3] != 7 && field_173[3] != 31 && field_173[3] != 59) {
            this.method_119();
            this.field_269 = field_173[3];
            this.field_270 = MenuUi.field_29;
            field_173[3] = 59;
            MenuUi.field_29 = 12;
            this.repaint();
         }

         this.method_63();
         if (!MessageScreens.field_67) {
            MessageScreens.field_68 = 0;
            this.field_258 = 0;
         }

         MessageScreens.field_67 = false;
         Gfx var71 = this.field_184;
      }
   }

   // $VF: renamed from: b (java.lang.String) void
   private void method_102(String var1) {
      int var4 = 44;
      String var5 = var1;
      Vector var6 = new Vector();
      Vector var10001;
      if (var1 != null && var1 != "") {
         int var3 = 0;

         int var2;
         while ((var2 = var5.indexOf(44, var3)) >= 0) {
            var6.addElement(var5.substring(var3, var2));
            var3 = var2 + 1;
         }

         if (var3 < var5.length()) {
            var6.addElement(var5.substring(var3));
         }

         var10001 = var6;
      } else {
         var10001 = var6;
      }

      this.field_271 = var10001;
   }

   // $VF: renamed from: x () void
   private void method_103() {
      if (field_173[52] == 1) {
         int var1;
         if (this.field_219 > 1) {
            var1 = field_173[71] + field_153 + field_162[71] + 3 + -4 + (this.field_220 << 4);
         } else {
            var1 = field_173[71] - 16 - 1;
         }

         this.field_184.setColor(this.field_214[this.field_220]);
         method_109(this.field_184, field_173[11] * 10, field_173[70] + field_152 - 15, var1);
         if (this.field_219 > 1) {
            this.field_184.setColor(-16777216);
            var1 = field_173[71] + field_153 + 1 + -4;
            int var2 = field_173[70] + 24;
            var2 += 4;
            this.field_184.fillRect(var2, var1, 42, 14);
            if (field_173[4] <= field_154 - 100) {
               var2 += 14;
            }

            if (field_173[4] <= field_154 - 10) {
               var2 += 14;
            }

            this.field_184.setColor(-1);
            method_109(this.field_184, field_154 - field_173[4], var2, var1);
         }

         field_173[52] = 0;
      }
   }

   // $VF: renamed from: c (int) int
   private static int method_104(int var0) {
      int var2 = -16777216;

      for (int var5 = 2; var5 >= 0; var5--) {
         int var3 = var5 << 3;
         int var1 = (var0 & 255 << var3) >> var3;
         int var4;
         if ((var4 = field_173[6] >> 1 & 7) > 3) {
            var4 = 7 - var4;
         }

         var4 = var1 >> 2 >> (var4 << 1);
         if ((field_173[6] & 4) != 0) {
            if ((var1 = var1 + var4) > 255) {
               var1 = 255;
            }
         } else if ((var1 = var1 - var4) < 0) {
            var1 = 0;
         }

         var2 |= var1 << var3;
      }

      return var2;
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, int, int, int) void
   final void method_105(Gfx var1, int var2, int var3, int var4) {
      var2 <<= 2;
      var1.drawRegion(this.field_187, field_149[var2++], field_149[var2++], field_149[var2++], field_149[var2], 0, var3, var4, 20);
   }

   // $VF: renamed from: b (javax.microedition.lcdui.Gfx, int, int, int) void
   private void method_106(Gfx var1, int var2, int var3, int var4) {
      var2 <<= 2;
      var1.drawRegion(this.field_190, field_159[var2++], field_159[var2++], field_159[var2++], field_159[var2], 0, var3, var4, 20);
   }

   // $VF: renamed from: c (javax.microedition.lcdui.Gfx, int, int, int) void
   private void method_107(Gfx var1, int var2, int var3, int var4) {
      var2 <<= 2;
      var1.setClip(0, 0, 360, 640);
      var1.drawRegion(this.field_188, field_162[var2++], field_162[var2++], field_162[var2++], field_162[var2], 0, var3, var4, 20);
   }

   // $VF: renamed from: d (javax.microedition.lcdui.Gfx, int, int, int) void
   private void method_108(Gfx var1, int var2, int var3, int var4) {
      var2 <<= 2;
      var1.drawRegion(this.field_189, field_161[var2++], field_161[var2++], field_161[var2++], field_161[var2], 0, 0, 0, 20);
   }

   // $VF: renamed from: e (javax.microedition.lcdui.Gfx, int, int, int) void
   private static void method_109(Gfx var0, int var1, int var2, int var3) {
      do {
         int var4 = var1 % 10;
         var1 /= 10;
         BitmapFont.field_85.method_42(var0, var2, var3, var4);
         var2 -= 14;
      } while (var1 != 0);
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Gfx, int) void
   private void method_110(Gfx var1, int var2) {
      var1.setClip(0, 0, 360, 640);
      var1.setColor(0);
      var1.fillRect(0, 0, 360, 640);
      if ((var2 & 1) == 0) {
         this.method_108(var1, 0, 0, 0);
      } else {
         this.method_108(var1, 1, 0, 0);
      }

      if ((var2 & 2) == 2) {
         int var4 = 0;

         for (int var3 = 0; var3 < field_151; var3++) {
            for (int var5 = 0; var5 < field_150; var5++) {
               if ((this.field_180[var4] & 15) < 3) {
                  this.method_105(var1, this.field_180[var4] & 15, var5 * 10, var3 * 10);
               }

               var4++;
            }

            var4 += 2;
         }

         var1.setColor(-82434);
         var1.fillRect(field_155[2][0] * 10 + field_148[7], field_155[2][1] * 10 + field_148[8], field_148[9], field_148[10]);
      }
   }

   // $VF: renamed from: b (javax.microedition.lcdui.Gfx) void
   private void method_111(Gfx var1) {
      var1.setClip(0, 0, 360, 640);
      var1.setColor(-16777216);
      var1.fillRect(0, 0, 360, 640);
      if (var1 != null) {
         Object var4 = null;
         int var5 = field_173[60] + 1;
         if (this.field_278.size() != 0) {
            var1.setFont(this.field_210);
            var1.setColor(65535);
            var4 = ((Command)this.field_278.firstElement()).getLabel();
            String var2 = ((Command)this.field_278.lastElement()).getLabel();
            if (!var4.equals(" ")) {
               BitmapFont.field_85.method_31(var1, (String)var4, 1, var5, 0);
            }

            if (!var2.equals(" ")) {
               int var9 = BitmapFont.field_85.method_34(var2, (byte)1);
               BitmapFont.field_85.method_31(var1, var2, 360 - (var9 + 1), var5, 0);
            }
         }
      }

      if (this.field_219 > 1) {
         var1.setColor(-1);
         var1.fillRect(field_173[70], field_173[71] + field_153 + field_162[71] + 3 + -4 - 1, field_152, 1);
      }

      for (int var8 = this.field_219 == 1 ? 0 : 4; var8 >= 0; var8--) {
         int var3;
         if (this.field_219 > 1) {
            var3 = field_173[71] + field_153 + field_162[71] + 3 + -4 + (var8 << 4);
         } else {
            var3 = field_173[71] - 16 - 1;
         }

         int var6 = this.field_214[var8];
         var1.setColor(var6);
         if (var8 < this.field_219) {
            method_109(var1, method_58(this.field_245[var8], 6, 3) * 10, field_173[70] + field_152 - 15, var3);
            this.method_112(var1, var8);
         } else {
            BitmapFont.field_85.method_42(var1, field_173[70] + field_152 - 15, var3, 52);
         }

         int var10;
         if (this.field_219 > 1) {
            var10 = field_173[70];
         } else if ((var10 = field_160[10] + 1) < field_173[70]) {
            var10 = field_173[70];
         }

         char[] var7;
         int var15;
         int var16;
         if (this.field_219 == 1) {
            var7 = field_127;
            var15 = 0;
            var16 = 3;
         } else {
            var7 = this.field_231;
            var15 = var8 * 7;
            var16 = 7;
            this.method_107(var1, 0 + (var8 == this.field_220 ? 5 : var8), var10, var3 + 1);
            var10 += 16;
         }

         var1.setColor(var6);
         BitmapFont.field_85.method_29(var1, var7, var15, var16, var10 + 2, var3, 20);
      }

      if (this.field_219 > 1) {
         int var12 = field_173[71] + field_153 + 2 + -4;
         int var11 = field_173[70];
         this.method_107(var1, 26, var11, var12);
      }
   }

   // $VF: renamed from: b (javax.microedition.lcdui.Gfx, int) void
   private void method_112(Gfx var1, int var2) {
      if (var2 < this.field_219) {
         int var4;
         if (this.field_219 > 1) {
            var4 = field_173[70] + field_152 - 15 - 84 - 16;
            int var3 = field_173[70] + 16 + 98 + 32;
            if (var4 < var3) {
               var4 = var3;
            }
         } else {
            var4 = field_173[70] + 32 + 1;
         }

         int var6;
         if (var2 == this.field_220) {
            var6 = field_173[28];
         } else {
            var6 = this.field_245[var2][9] >> 2 & 3;
            int var5;
            if ((var5 = this.field_245[var2][0]) != 26 && var5 != 0) {
               var6++;
            }
         }

         if (var6 > 3) {
            var6 = 3;
         }

         int var7;
         if (this.field_219 > 1) {
            var7 = field_173[71] + field_153 + field_162[71] + 3 + -4 + (var2 << 4) + 1;
         } else {
            var7 = field_173[71] + field_153 + 1;
         }

         var1.setColor(-16777216);
         var1.setClip(var4 - 32, var7, 48, 16);
         var1.fillRect(var4 - 32, var7, 48, 16);

         while (var6 > 0) {
            this.method_107(var1, 25, var4, var7);
            var4 -= 16;
            var6--;
         }
      }
   }

   // $VF: renamed from: c (javax.microedition.lcdui.Gfx) void
   private void method_113(Gfx var1) {
      int var4;
      if ((var4 = field_173[25] - 5) < 0) {
         var4 = 0;
      }

      for (int var3 = var4; var3 <= field_173[25]; var3++) {
         int var2 = var3;
         if (var3 > 20) {
            var2 = 20;
         }

         this.method_107(
            var1, 17 + this.field_166[var2][0], field_173[70] + field_152 - (field_162[70] + 1) * (1 + (var3 - var4)), field_173[71] + field_153 + 1 + -4
         );
      }
   }

   public final int getGameAction(int var1) {
      int var2 = -28263;

      try {
         var2 = super.getGameAction(var1);
      } catch (Exception var3) {
      }

      if (var1 == -1) {
         var2 = 1;
      } else if (var1 == -4) {
         var2 = 5;
      } else if (var1 == -2) {
         var2 = 6;
      } else if (var1 == -3) {
         var2 = 2;
      } else if (var1 == -5) {
         var2 = 8;
      }

      if (var1 == 50) {
         var2 = 1;
      } else if (var1 == 54) {
         var2 = 5;
      } else if (var1 == 56) {
         var2 = 6;
      } else if (var1 == 52) {
         var2 = 2;
      } else if (var1 == 53) {
         var2 = 8;
      }

      return var2;
   }

   public final void keyPressed(int var1) {
      if (!this.field_268) {
         field_120 = false;
         this.field_272 = var1;
         this.field_273 = var1;
         this.field_276 = false;
         this.field_277 = false;
         if (field_173[3] != 11 && field_173[3] != 18 || this.field_219 <= 1 || field_121 || var1 == -7 || var1 == -6) {
            Command var2 = null;

            try {
               if (var1 == -6) {
                  var2 = (Command)this.field_278.firstElement();
               } else if (this.field_278.size() > 1 && var1 == -7) {
                  var2 = (Command)this.field_278.lastElement();
               }

               if (var2 == this.field_146 && (field_173[3] == 34 || field_173[3] == 38)) {
                  var2 = null;
                  var1 = -5;
               }

               if (var2 != null && var2 != this.field_142 && (this.field_219 < 2 || field_173[3] != 10 && field_173[3] != 17)) {
                  if (this.field_219 > 1 && field_121) {
                     if (field_173[3] != 11) {
                        if (field_173[3] == 52 && var2 == this.field_146) {
                           this.method_118(var2);
                           return;
                        }

                        this.method_118(var2);
                        return;
                     }

                     if (var2 == this.field_137 || var2 == this.field_146 && MenuUi.field_30 == MenuUi.method_16(98)) {
                        this.field_208 = 8;
                        return;
                     }

                     if (var2 == this.field_146) {
                        this.method_118(var2);
                        return;
                     }
                  } else {
                     this.method_118(var2);
                  }

                  return;
               }
            } catch (Exception var4) {
            }

            if (field_173[3] == 34 && this.field_223 != 0 && var2 != this.field_137) {
               this.field_272 = 9999;
               this.field_249 = false;
            } else {
               int var5 = 0;

               try {
                  var5 = this.getGameAction(var1);
               } catch (IllegalArgumentException var3) {
               }

               this.field_274 &= 33554430;
               if (field_173[3] == 23) {
                  this.field_274 = this.field_274 | NicknameEditor.method_26(var1, var5);
               }

               if (this.field_219 > 1 && field_173[3] == 11 && (var1 == -6 || var1 == -5 || var5 == 8) && field_121) {
                  if (MenuUi.field_30 == MenuUi.method_16(98)) {
                     this.field_208 = 8;
                     this.field_272 = 9999;
                     this.field_249 = false;
                     return;
                  }
               } else {
                  if (var5 == 2 && field_173[3] != 11 && field_173[3] != 52 && field_173[3] != 55) {
                     this.field_208 = 4;
                     return;
                  }

                  if (var5 == 5 && field_173[3] != 11 && field_173[3] != 52 && field_173[3] != 55) {
                     this.field_208 = 8;
                     return;
                  }

                  if (var5 == 1) {
                     this.field_208 = 1;
                     return;
                  }

                  if (var5 == 6) {
                     this.field_208 = 2;
                     return;
                  }

                  if (var5 == 8 && (this.field_219 < 2 || field_173[3] != 10 && field_173[3] != 17)) {
                     this.field_208 = 16;
                     return;
                  }

                  if (var1 == -6 && this.field_219 > 1 && (field_173[3] == 10 || field_173[3] == 17)) {
                     if (field_173[3] == 10) {
                        this.field_209 = this.field_208;
                     }

                     this.field_275 = true;
                     this.field_208 = 16;
                  }
               }
            }
         }
      }
   }

   public final void keyRepeated(int var1) {
   }

   public final void keyReleased(int var1) {
      if (this.field_276) {
         this.field_272 = 9999;
      } else {
         this.field_277 = true;
      }

      int var2 = 0;

      try {
         var2 = this.getGameAction(var1);
      } catch (IllegalArgumentException var3) {
      }

      if (field_173[3] == 23) {
         this.field_274 = this.field_274 & ~NicknameEditor.method_26(var1, var2);
      }
   }

   // $VF: renamed from: y () void
   private void method_114() {
      int var1 = -1;
      if (this.field_275 && (this.field_208 & 16) != 0) {
         var1 = 4;
      } else if ((this.field_208 & 8) != 0) {
         var1 = 0;
      } else if ((this.field_208 & 4) != 0) {
         var1 = 2;
      } else if ((this.field_208 & 2) != 0) {
         var1 = 1;
      } else if ((this.field_208 & 1) != 0) {
         var1 = 3;
      }

      if (var1 != -1) {
         if (this.field_275) {
            var1 |= 8;
            this.field_208 = 0;
         }

         this.field_213[this.field_221 + (this.field_219 > 1 ? 5 : 0)] = var1;
      }
   }

   // $VF: renamed from: b () int
   private int method_115() {
      int var1 = 0;

      try {
         if (this.field_272 != 9999) {
            switch (this.getGameAction(this.field_272)) {
               case 1:
                  var1 = 2;
                  break;
               case 2:
                  var1 = 4;
               case 3:
               case 4:
               case 7:
               default:
                  break;
               case 5:
                  var1 = 32;
                  break;
               case 6:
                  var1 = 64;
                  break;
               case 8:
                  var1 = 256;
            }
         }
      } catch (Exception var3) {
      }

      switch (this.field_272) {
         case -5:
            var1 = 256;
            break;
         case -4:
            var1 = 32;
            break;
         case -3:
            var1 = 4;
            break;
         case -2:
            var1 = 64;
            break;
         case -1:
            var1 = 2;
            break;
         case 50:
            var1 = 2;
            break;
         case 52:
            var1 = 4;
            break;
         case 53:
            var1 = 256;
            break;
         case 54:
            var1 = 32;
            break;
         case 56:
            var1 = 64;
      }

      if (this.field_277) {
         this.field_272 = 9999;
         this.field_249 = false;
      }

      this.field_276 = true;
      return var1;
   }

   // $VF: renamed from: c () int
   private int method_116() {
      int var1 = 0;

      for (int var2 = this.field_219 - 1; var2 >= 0; var2--) {
         switch (this.method_117(var2)) {
            case 0:
               var1 = 32;
               break;
            case 1:
               var1 = 64;
               break;
            case 2:
               var1 = 4;
               break;
            case 3:
               var1 = 2;
               break;
            case 4:
               var1 = 256;
         }

         if (var1 != 0) {
            break;
         }
      }

      return var1;
   }

   // $VF: renamed from: d (int) int
   private int method_117(int var1) {
      int var2 = -1;
      byte[] var3 = this.field_223 == 0 ? this.field_227 : this.field_226;
      var1 = this.method_93(var1);
      if ((var3[0] & 4 << var1) != 0) {
         if ((var3[0] & 128) != 0) {
            var2 = 4;
         } else {
            var2 = this.field_213[var1];
         }
      }

      return var2;
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Command) void
   private void method_118(Command var1) {
      if (this.field_143 == null && var1 != this.field_142) {
         if (var1 == this.field_139 && field_173[3] != 3) {
            this.method_143();
            return;
         }

         this.field_143 = var1;
      }
   }

   // $VF: renamed from: z () void
   private void method_119() {
      this.removeCommand(this.field_137);
      this.removeCommand(this.field_138);
      this.removeCommand(this.field_139);
      this.removeCommand(this.field_140);
      this.removeCommand(this.field_142);
      this.removeCommand(this.field_141);
      this.removeCommand(this.field_145);
      this.removeCommand(this.field_144);
      this.removeCommand(this.field_146);
      this.removeCommand(this.field_147);
      this.field_278.removeAllElements();
   }

   public final void addCommand(Command var1) {
      try {
         this.field_278.addElement(var1);
      } catch (Exception var2) {
      }
   }

   public final void removeCommand(Command var1) {
      try {
         this.field_278.removeElement(var1);
      } catch (Exception var2) {
      }
   }

   // $VF: renamed from: A () void
   private void method_120() {
      String var1 = this.method_144("NAMCO-AVAILABLE-LANGUAGES");
      this.field_279 = new String[var1.length() / 3 + 1];
      MenuUi.field_27[0] = new String[this.field_279.length];

      for (int var2 = 0; var2 < this.field_279.length; var2++) {
         this.field_279[var2] = var1.substring(var2 * 3, 2 + var2 * 3);
         int var3 = 0;

         while (!this.field_280[var3].equals(this.field_279[var2])) {
            var3 += 3;
         }

         MenuUi.field_27[0][var2] = this.field_280[var3 + 1];
         MenuUi.field_26[0] = new String[]{this.field_280[var3 + 2]};
      }
   }

   // $VF: renamed from: B () void
   private void method_121() {
      if (this.field_123 == null) {
         if (this.method_144("NAMCO-AVAILABLE-LANGUAGES") == null) {
            this.field_123 = new Localization("en", false, this.field_247);
            return;
         }

         if (field_164[27] < this.field_279.length && field_164[27] != -1) {
            MenuUi.field_30 = field_164[27];
         } else {
            MenuUi.field_30 = 1;
         }

         int var1 = 1;
         this.method_115();

         for (; this.field_123 == null; this.method_63()) {
            Thread.yield();
            field_173[8] = field_173[7];
            field_173[7] = this.method_115() | this.field_274;
            field_173[9] = (field_173[7] ^ field_173[8]) & field_173[7];
            int var2 = field_173[9];
            if (this.field_143 == this.field_147) {
               var2 = 256;
            }

            if (MenuUi.method_13(var2)) {
               if (field_164[27] != MenuUi.field_30) {
                  field_164[27] = (byte)MenuUi.field_30;
               }

               this.method_60();
               field_173[42] = 0;
               this.field_123 = new Localization(this.field_279[MenuUi.field_30], false, this.field_247);
            }

            if (var1 != 0 || (var2 & 2) != 0 || (var2 & 64) != 0) {
               var1 = 0;

               while (!this.field_280[var1 * 3 + 1].equals(MenuUi.field_27[0][MenuUi.field_30])) {
                  var1++;
               }

               MenuUi.field_26[0][0] = this.field_280[var1 * 3 + 2];
               MenuUi.field_49 = 1;
            }

            var1 = 0;
            this.repaint();
            this.serviceRepaints();

            try {
               Thread.sleep(10L);
            } catch (Exception var3) {
            }
         }
      }
   }

   // $VF: renamed from: a (javax.microedition.lcdui.Command, javax.microedition.lcdui.Command) void
   private void method_122(Command var1, Command var2) {
      this.method_119();
      this.addCommand(var1);
      this.addCommand(var2);
   }

   // $VF: renamed from: C () void
   private void method_123() {
      if (this.field_219 > 1) {
         if (this.field_223 == 0) {
            GameScreen var1 = this;
            boolean more = true;
            if (this.field_225 == Integer.MAX_VALUE) {
               this.field_225 = this.field_219 - 2;
            }

            while (more && var1.field_225 >= 0) {
               if (more = var1.method_132(var1.field_225) != 0) {
                  var1.field_213[5 + var1.method_93(var1.field_225 + 1)] = var1.field_226[0] & 15;
                  var1.field_225--;
               }
            }

            if (more) {
               var1.field_225 = Integer.MAX_VALUE;
            }

            if (!this.method_128() && this.field_225 == Integer.MAX_VALUE) {
               System.arraycopy(this.field_213, 5, this.field_213, 0, 5);
               if ((this.field_213[this.field_221] & 12) != 0) {
                  this.field_213[5 + this.field_221] = this.field_213[5 + this.field_221] & -13;
               }

               var1 = this;
               int var2 = 0;
               int var3 = 0;

               for (int var4 = 4; var4 >= 0; var4--) {
                  int var9;
                  var3 = (var9 = var3 << 2) | var1.field_213[var4] & 3;
                  if ((var1.field_213[var4] & 8) != 0) {
                     var2 |= 1024 << var4;
                     if ((var1.field_213[var4] & 4) != 0) {
                        var2 |= 32768;
                     }

                     var1.field_213[var4] = var1.field_213[var4] & -13;
                  }
               }

               var3 |= var2;
               if (var1.field_175 > 32767) {
                  var1.field_175 = 32767;
               }

               method_59(var3, var1.field_227, 0, 2);
               method_59(var1.field_175, var1.field_227, 2, 2);
               if (++var1.field_284 > 3) {
                  var1.field_284 = 0;
                  if (field_173[3] == 10) {
                     var1.field_227[4] = (byte)(field_173[16] - 128);
                     var1.field_227[5] = (byte)(field_173[17] - 128);

                     for (int var13 = 1; var13 < 5; var13++) {
                        var1.field_227[4 + (var13 << 1)] = (byte)(var1.field_174[var13 - 1][0] - 128);
                        var1.field_227[4 + (var13 << 1) + 1] = (byte)(var1.field_174[var13 - 1][1] - 128);
                     }
                  }
               } else {
                  for (int var14 = 0; var14 < 5; var14++) {
                     var1.field_227[4 + (var14 << 1)] = 0;
                     var1.field_227[4 + (var14 << 1) + 1] = 0;
                  }
               }

               var1.method_134(-1);
               this.method_114();
               return;
            }
         } else {
            GameScreen var6 = this;
            this.field_225 = 0;
            if (this.method_132(0) != 0) {
               int var8 = method_58(this.field_226, 0, 2);

               for (int var11 = 0; var11 < 5; var11++) {
                  var6.field_213[var11] = var8 & 3;
                  var8 >>= 2;
               }

               var6.field_175 = method_58(var6.field_226, 2, 2);
               if (field_173[3] == 10 && var6.field_226[4] != 0 && var6.field_226[5] != 0) {
                  if (var6.field_226[4] + 128 != field_173[16] || var6.field_226[5] + 128 != field_173[17]) {
                     field_173[16] = var6.field_226[4] + 128;
                     field_173[17] = var6.field_226[5] + 128;
                  }

                  for (int var12 = 1; var12 < 5; var12++) {
                     if (var6.field_226[4 + (var12 << 1)] + 128 != var6.field_174[var12 - 1][0]
                        || var6.field_226[4 + (var12 << 1) + 1] + 128 != var6.field_174[var12 - 1][1]) {
                        var6.field_174[var12 - 1][0] = var6.field_226[4 + (var12 << 1)] + 128;
                        var6.field_174[var12 - 1][1] = var6.field_226[4 + (var12 << 1) + 1] + 128;
                     }
                  }
               }

               var6.field_225 = Integer.MAX_VALUE;
            }

            if (!this.method_128() && this.field_225 == Integer.MAX_VALUE) {
               if ((this.field_226[0] & 4 << this.field_221) != 0) {
                  this.field_213[this.field_221 + 5] = this.field_213[this.field_221 + 5] & -13;
               }

               this.method_114();
               this.method_124();
            }
         }
      }
   }

   // $VF: renamed from: D () void
   private void method_124() {
      this.field_227[0] = (byte)this.field_213[this.field_221 + 5];
      this.method_134(0);
   }

   // $VF: renamed from: E () void
   private void method_125() {
      if (this.field_236 != null) {
         for (int var1 = this.field_236.length - 1; var1 >= 0; var1--) {
            if (this.field_236[var1] != null) {
               try {
                  this.field_236[var1].close();
               } catch (Exception var2) {
               }
            }
         }
      }
   }

   // $VF: renamed from: F () void
   private void method_126() {
      this.field_236 = null;
      this.field_226 = null;
      this.field_227 = null;
      this.field_224 = null;
      System.gc();
   }

   // $VF: renamed from: G () void
   private void method_127() {
      this.field_224 = new int[this.field_236.length];
   }

   // $VF: renamed from: d () boolean
   private boolean method_128() {
      boolean var1 = false;

      for (int var2 = this.field_224.length - 1; var2 >= 0; var2--) {
         if (this.field_224[var2] == 1) {
            var1 = true;
            break;
         }
      }

      return var1;
   }

   // $VF: renamed from: H () void
   private void method_129() {
      this.field_223 = 0;

      int var2;
      for (int var3 = (var2 = MessageScreens.field_63[5].length) - 8; var3 < var2; var3++) {
         MessageScreens.field_63[5][var3] = field_126[(field_173[6] + var3) % field_126.length];
      }

      MessageScreens.field_66 = 5;
      this.field_232 = false;
      this.field_233 = LanDiscovery.start(6000, () -> this.field_232 = true);
      field_173[3] = 25;
   }

   // $VF: renamed from: I () void
   private void method_130() throws IOException {
      this.field_223 = 1;
      int var2 = MessageScreens.field_63[6].length - 1;
      MessageScreens.field_63[6][var2] = field_126[field_173[6] % field_126.length];
      MessageScreens.field_66 = 6;
      this.field_232 = false;
      this.field_235 = new LanListener(
         new String(NicknameEditor.field_60).trim() + '_' + this.method_144("NAMCO-VS-LOOP") + '_' + this.method_144("NAMCO-VS-WAIT")
      );
      field_173[85] = 3600000;
      field_173[3] = 29;
      this.field_236 = new LanLink[1];
      this.field_226 = new byte[14];
      this.field_227 = new byte[6];
      new AcceptThread(this).start();
   }

   // $VF: renamed from: a (int, byte[], int) int
   private int method_131(int var1, byte[] var2, int var3) {
      int var4 = 0;

      while (var4 < var3 && this.field_224[0] == 0) {
         while ((var1 = this.method_132(0)) == 0 && this.field_224[0] == 0) {
         }

         if (var1 < this.field_226.length) {
            break;
         }

         if ((var1 = var3 - var4) > this.field_226.length) {
            var1 = this.field_226.length;
         }

         System.arraycopy(this.field_226, 0, var2, var4, var1);
         var4 += this.field_226.length;
      }

      return var4;
   }

   // $VF: renamed from: e (int) int
   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private int method_132(int var1) {
      int var2 = 0;
      int var3 = 0;
      long var4 = System.currentTimeMillis();
      long var6 = 0L;

      while (true) {
         boolean var10 = false /* VF: Semaphore variable */;

         label60: {
            try {
               var10 = true;
               if (!this.field_236[var1].ready() && var6 < 1500L) {
                  Thread.yield();
                  if ((var6 = System.currentTimeMillis() - var4) < 0L) {
                     var6 = 1500L;
                  }
                  continue;
               }

               if (var6 < 1500L) {
                  if ((var2 = this.field_236[var1].receive(this.field_226)) != this.field_226.length) {
                     var3 = 1;
                     var10 = false;
                  } else {
                     var10 = false;
                  }
               } else {
                  var10 = false;
               }
               break label60;
            } catch (Exception var11) {
               var10 = false;
            } finally {
               if (var10) {
                  this.field_224[var1] = 0;
               }
            }

            this.field_224[var1] = 1;
            break;
         }

         this.field_224[var1] = var3;
         break;
      }

      return var2;
   }

   // $VF: renamed from: a (int, byte[], int) void
   private void method_133(int var1, byte[] var2, int var3) {
      var1 = 0;

      while (var1 < var3) {
         int var4;
         if ((var4 = var3 - var1) > this.field_227.length) {
            var4 = this.field_227.length;
         }

         System.arraycopy(var2, var1, this.field_227, 0, var4);
         this.method_134(-1);
         var1 += this.field_227.length;
      }
   }

   // $VF: renamed from: d (int) void
   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private void method_134(int var1) {
      if (var1 == -1) {
         for (int var8 = this.field_236.length - 1; var8 >= 0; var8--) {
            this.method_134(var8);
         }
      } else {
         boolean var5 = false /* VF: Semaphore variable */;

         label46: {
            try {
               var5 = true;
               this.field_236[var1].send(this.field_227);
               var5 = false;
               break label46;
            } catch (Exception var6) {
               var5 = false;
            } finally {
               if (var5) {
                  this.field_224[var1] = 1;
               }
            }

            this.field_224[var1] = 1;
            return;
         }

         this.field_224[var1] = 0;
      }
   }

   // $VF: renamed from: J () void
   private void method_135() {
      this.field_139 = new Command(Localization.field_115[79], 1, 3);
      this.field_140 = new Command(Localization.field_115[80], 1, 2);
      this.field_137 = new Command(Localization.field_115[78], 2, 3);
      this.field_138 = new Command(Localization.field_115[77], 1, 1);
      this.field_141 = new Command(Localization.field_115[57], 1, 2);
      this.field_145 = new Command(Localization.field_115[56], 1, 1);
      this.field_144 = new Command(Localization.field_115[66], 1, 2);
      this.field_146 = new Command(Localization.field_115[58], 1, 1);
   }

   // $VF: renamed from: a (java.lang.String) java.lang.String
   public final String method_136(String var1) {
      return this.method_144(var1);
   }

   // $VF: renamed from: b () void
   public final void method_138() {
      this.method_143();
      this.field_179.notifyDestroyed();
   }

   // $VF: renamed from: a (byte, java.lang.String) void
   public final void method_140(byte var1, String var2) {
      this.field_286 = var1;
      this.field_285 = var2;
   }

   // $VF: renamed from: c () void
   public final void method_141() {
      switch (field_173[3]) {
         case 13:
            this.field_129.resume();
         case 10:
         case 11:
            this.field_262 = true;
         case 12:
         default:
            MenuUi.field_49 = 3;
      }
   }

   // $VF: renamed from: d () void
   public final void method_142() {
      switch (field_173[3]) {
         case 7:
         case 29:
            if (this.field_223 != 0) {
               field_173[85] = 500;
               field_173[85] = field_173[85] = 3600000;
            }
         case 8:
         case 9:
         case 12:
         case 14:
         case 15:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 30:
         case 31:
         case 32:
         default:
            break;
         case 10:
            if (this.field_219 == 1) {
               MenuUi.field_29 = 2;
               MenuUi.field_30 = 0;
               field_173[3] = 11;
               MenuUi.field_35 = false;
               this.field_262 = true;
            } else if (this.field_224 != null) {
               this.field_224[0] = 1;
            }

            field_173[0] = 2;
            this.field_129.suspend();
            break;
         case 11:
            field_173[0] = 2;
            this.field_262 = true;
            if (this.field_219 > 1 && this.field_224 != null) {
               this.field_224[0] = 1;
            }
            break;
         case 13:
            field_173[0] = 2;
            this.field_129.suspend();
            if (this.field_219 > 1 && this.field_224 != null) {
               this.field_224[0] = 1;
            }
            break;
         case 16:
         case 17:
         case 18:
            if (this.field_219 > 1 && this.field_224 != null) {
               this.field_224[0] = 1;
            }
            break;
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
            if (this.field_224 != null) {
               this.field_224[0] = 1;
            }
      }

      field_173[98] = 0;
      field_173[8] = 0;
      field_173[7] = 0;
      field_173[9] = 0;
      this.field_274 = 0;
      MenuUi.field_49 = 3;
      this.field_143 = null;
      this.field_246 = false;
   }

   // $VF: renamed from: e () void
   public final void method_143() {
      this.method_125();
      this.method_126();

      this.field_132 = false;
   }

   // $VF: renamed from: b (java.lang.String) java.lang.String
   private String method_144(String var1) {
      return this.field_179.getAppProperty(var1);
   }
}
