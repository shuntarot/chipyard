//******************************************************************************
// Copyright (c) 2019 - 2019, The Regents of the University of California (Regents).
// All Rights Reserved. See LICENSE and LICENSE.SiFive for license details.
//------------------------------------------------------------------------------

package chipyard

import chisel3._

import freechips.rocketchip.config.{Parameters}
import freechips.rocketchip.subsystem._
import freechips.rocketchip.tilelink._
import freechips.rocketchip.devices.tilelink._
import freechips.rocketchip.diplomacy._
import freechips.rocketchip.util.{DontTouch}

// ---------------------------------------------------------------------
// Base system that uses the debug test module (dtm) to bringup the core
// ---------------------------------------------------------------------

/**
 * Base top with periphery devices and ports, and a BOOM + Rocket subsystem
 */
class System(implicit p: Parameters) extends Subsystem
  with HasHierarchicalBusTopology
  with HasAsyncExtInterrupts
  with CanHaveMasterAXI4MemPort
  with CanHaveMasterAXI4MMIOPort
  with CanHaveSlaveAXI4Port
  // with HasPeripheryBootROM
{
  override lazy val module = new SystemModule(this)
  // Replace BootROM with MaskROM
  val maskROMParams = MaskROMParams(address = 0x10000, name = "BootROM")
  val maskROM = MaskROM.attach(maskROMParams, sbus)
}

/**
 * Base top module implementation with periphery devices and ports, and a BOOM + Rocket subsystem
 */
class SystemModule[+L <: System](_outer: L) extends SubsystemModuleImp(_outer)
  with HasRTCModuleImp
  with HasExtInterruptsModuleImp
  with CanHaveMasterAXI4MemPortModuleImp
  with CanHaveMasterAXI4MMIOPortModuleImp
  with CanHaveSlaveAXI4PortModuleImp
  // with HasPeripheryBootROMModuleImp
  with DontTouch {
    global_reset_vector := 0x10040.U
  }
