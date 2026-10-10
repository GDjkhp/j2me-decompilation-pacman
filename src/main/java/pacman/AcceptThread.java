package pacman;

// $VF: renamed from: c
final class AcceptThread extends Thread {
   // $VF: renamed from: a g
   private final GameScreen field_104;

   AcceptThread(GameScreen var1) {
      this.field_104 = var1;
   }

   // $VF: renamed from: run () void
   public final void run() {
      try {
         this.field_104.field_236[0] = this.field_104.field_235.accept();
         this.field_104.field_226[0] = 0;
      } catch (Exception var9) {
         if (this.field_104.field_226 != null) {
            this.field_104.field_226[0] = 1;
         }
      } finally {
         synchronized (this) {
            this.field_104.field_232 = true;
            this.notifyAll();
         }
      }
   }
}
