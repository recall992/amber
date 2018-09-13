package org.amber.common

import org.slf4j.LoggerFactory

trait LogSupport {
  /**
    * Logger
    */
  protected val log = LoggerFactory.getLogger(this.getClass)


}
