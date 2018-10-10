/* Copyright 2017-18, Emmanouil Antonios Platanios. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.platanios.tensorflow.api.ops.rnn

import org.platanios.tensorflow.api.core.Shape
import org.platanios.tensorflow.api.ops.control_flow.WhileLoopVariable
import org.platanios.tensorflow.api.ops._

/**
  * @author Emmanouil Antonios Platanios
  */
package object attention {
  /** State of the attention wrapper RNN cell.
    *
    * @param  cellState         Wrapped cell state.
    * @param  time              Scalar containing the current time step.
    * @param  attention         Attention emitted at the previous time step.
    * @param  alignments        Alignments emitted at the previous time step for each attention mechanism.
    * @param  alignmentsHistory Alignments emitted at all time steps for each attention mechanism. Call `stack()` on
    *                           each of the tensor arrays to convert them to tensors.
    * @param  attentionState    Attention cell state.
    */
  case class AttentionWrapperState[AttentionDataType, CellState, AttentionState](
      cellState: CellState,
      time: Output[Int],
      attention: Output[AttentionDataType],
      alignments: Seq[Output[AttentionDataType]],
      alignmentsHistory: Seq[TensorArray[AttentionDataType]],
      attentionState: AttentionState)

  private[rnn] trait API {
    type Attention[AttentionDataType, CellState, AttentionState] = attention.Attention[AttentionDataType, CellState, AttentionState]
    type BahdanauAttention[T] = attention.BahdanauAttention[T]
    type LuongAttention[T] = attention.LuongAttention[T]
    type AttentionWrapperCell[T, S, SS, AS, ASS] = attention.AttentionWrapperCell[T, S, SS, AS, ASS]

    val LuongAttention      : attention.LuongAttention.type       = attention.LuongAttention
    val BahdanauAttention   : attention.BahdanauAttention.type    = attention.BahdanauAttention
    val AttentionWrapperCell: attention.AttentionWrapperCell.type = attention.AttentionWrapperCell
  }
}
