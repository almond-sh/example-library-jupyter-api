package almond.example

object Example {

  /**
    * Displays a timer, blocking until the timer is over.
    */
  def displayTimer()(implicit kernel: almond.api.JupyterApi): Unit = {
    val count = 5
    val id = java.util.UUID.randomUUID().toString
    kernel.publish.html(s"<b>$count</b>", id)
    for (i <- (0 until count).reverse) {
      Thread.sleep(1000L)
      kernel.publish.updateHtml(s"<b>$i</b>", id)
    }
    Thread.sleep(200L)
    kernel.publish.updateHtml(Character.toChars(0x1f981).mkString, id)
  }

  /**
    * Displays a timer in background, returning as soon as the initial
    * value is displayed.
    */
  def displayTimerBg()(implicit kernel: almond.api.JupyterApi): Unit = {
    val count = 5
    val id = java.util.UUID.randomUUID().toString
    kernel.publish.html(s"<b>$count</b>", id)

    def background(): Unit = {
      for (i <- (0 until count).reverse) {
        Thread.sleep(1000L)
        kernel.publish.updateHtml(s"<b>$i</b>", id)
      }
      Thread.sleep(200L)
      kernel.publish.updateHtml(Character.toChars(0x1f981).mkString, id)
    }

    val t = new Thread() {
      setDaemon(true)
      override def run() =
        background()
    }

    t.start()
  }

}
