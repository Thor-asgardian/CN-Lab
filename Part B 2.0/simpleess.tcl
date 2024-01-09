set ns [new Simulator]

set topo [new Topography]
$topo load_flatgrid 1500 1500

set tracefile [open p4.tr w]
$ns trace-all $tracefile

#Open the NAM trace file
set namfile [open p4.nam w]
$ns namtrace-all $namfile
$ns namtrace-all-wireless $namfile 1500 1500

$ns node-config -adhocRouting DSDV \
-llType LL \
-macType Mac/802_11 \
-ifqType Queue/DropTail \
-ifqLen 20 \
-phyType Phy/WirelessPhy \
-channelType Channel/WirelessChannel \
-propType Propagation/TwoRayGround \
-antType Antenna/OmniAntenna \
-topoInstance $topo \
-agentTrace ON \
-routerTrace ON

create-god 4

set n0 [$ns node]
$n0 set X_ 630
$n0 set Y_ 501
$n0 set Z_ 0.0
$ns initial_node_pos $n0 20
set n1 [$ns node]
$n1 set X_ 454
$n1 set Y_ 340
$n1 set Z_ 0.0
$ns initial_node_pos $n1 20
set n2 [$ns node]
$n2 set X_ 785
$n2 set Y_ 326
$n2 set Z_ 0.0
$ns initial_node_pos $n2 20
set n3 [$ns node]
$n3 set X_ 270
$n3 set Y_ 190
$n3 set Z_ 0.0
$ns initial_node_pos $n3 20

#Agents Definition
#Setup a UDP connection
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set null1 [new Agent/Null]
$ns attach-agent $n4 $null1
$ns connect $udp0 $null1

#Setup a TCP connection
set tcp0 [new Agent/TCP]
$ns attach-agent $n3 $tcp0
set sink1 [new Agent/TCPSink]
$ns attach-agent $n5 $sink1
$ns connect $tcp0 $sink1

set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
$cbr0 set packetSize_ 1000
$cbr0 set rate_ 1.0Mb
$cbr0 set random_ null

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

proc finish {} {
global ns tracefile namfile
$ns flush-trace
close $tracefile
close $namfile

exec nam p4.nam &
exec echo "Number of packets dropped is:" & 
exec grep -c "^D" p4.tr &
exit 0

}

$ns at 1.0 "$cbr0 start"
$ns at 2.0 "$ftp0 start"
$ns at 180.0 "$ftp0 stop"
$ns at 200.0 "$cbr0 stop"
$ns at 200.0 "finish"
$ns at 70 "$n0 set dest 100 60 20"
$ns at 100 "$n1 set dest 700 300 20"
$ns at 150 "$n2 set dest 900 200 20"
$ns at 150 "$n3 set dest 800 500 30"
$ns run
