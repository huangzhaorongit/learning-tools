package com.criteo.dev.cluster.copy

import com.criteo.dev.cluster.Node

/**
  * Any special handling needed for copy table.  For exmaple, copy over meta files, etc.
  *
  * A file copier is provided, with source and destination nodes.
  */
trait CopyTableListener {

  def onCopy(tableInfo: TableInfo, copyFileAction: CopyFileAction, source: Node, target: Node)
}
