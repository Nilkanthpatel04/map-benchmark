### About the Project

This is developed to single-node benchmark the various implemetation of java.util.Map iterface.
Following implementation have been benchmarked and tests for same are included with this codebase.
1. java (1.8) ConcurrentHashMap
2. Hazelcast Map
3. Apache Geode/Gemfire Map (a.k.a Region)

### How to build

Recommended to import the project in IntelliJ.

command-line.
mvn clean package

Note: this will build and run a benchmark tests as well.

Details about sample run carried out on my local laptop machine.

### Benchmark Java (jdk 1.8) ConcurrentHashMap

#### Test-1. putBenchmarkTest
```
itr = 0 throughput =532410
 itr = 1 throughput =597504
 itr = 2 throughput =612358
 itr = 3 throughput =539334
 itr = 4 throughput =492229
 itr = 5 throughput =670195
 itr = 6 throughput =637665
 itr = 7 throughput =620296
 itr = 8 throughput =663232
 itr = 9 throughput =569964
# Throughput     Put : min= 492229.0, max= 670195.0, avg= 593518.700 --> [532410, 597504, 612358, 539334, 492229, 670195, 637665, 620296, 663232, 569964]
```

#### Test-2. getBenchmarkTest
```
itr = 0 throughput =3631107
 itr = 1 throughput =4005909
 itr = 2 throughput =4157168
 itr = 3 throughput =3556118
 itr = 4 throughput =4368386
 itr = 5 throughput =4205278
 itr = 6 throughput =4253309
 itr = 7 throughput =4229220
 itr = 8 throughput =3973315
 itr = 9 throughput =4422983
# Throughput     GET : min= 3556118.0, max= 4422983.0, avg= 4080279.300 --> [3631107, 4005909, 4157168, 3556118, 4368386, 4205278, 4253309, 4229220, 3973315, 4422983]
```

#### Test-3. putGetBenchmarkTest
```
itr = 0 throughput =930321
 itr = 1 throughput =969406
 itr = 2 throughput =1113752
 itr = 3 throughput =995423
 itr = 4 throughput =1089616
 itr = 5 throughput =1202523
 itr = 6 throughput =1243552
 itr = 7 throughput =1286588
 itr = 8 throughput =1065710
 itr = 9 throughput =976814
# Throughput PutGet5050 : min= 930321.0, max= 1286588.0, avg= 1087370.500 --> [930321, 969406, 1113752, 995423, 1089616, 1202523, 1243552, 1286588, 1065710, 976814]
```

### Benchmark Hazelcast Map

#### Test-1. putBenchmarkTest
```
Aug 19, 2017 1:26:26 PM com.hazelcast.internal.partition.impl.PartitionStateManager
INFO: [192.168.1.5]:5701 [dev] [3.8.3] Initializing cluster partition table arrangement...
 itr = 0 throughput =610989
 itr = 1 throughput =571038
 itr = 2 throughput =662010
 itr = 3 throughput =536513
 itr = 4 throughput =691873
 itr = 5 throughput =688721
 itr = 6 throughput =706887
 itr = 7 throughput =668923
 itr = 8 throughput =700911
 itr = 9 throughput =719220
# Throughput     Put : min= 536513.0, max= 719220.0, avg= 655708.500 --> [610989, 571038, 662010, 536513, 691873, 688721, 706887, 668923, 700911, 719220]
```

#### Test 2. getBenchmarkTest
```
Aug 19, 2017 1:20:00 PM com.hazelcast.internal.partition.impl.PartitionStateManager
INFO: [192.168.1.5]:5701 [dev] [3.8.3] Initializing cluster partition table arrangement...
 itr = 0 throughput =3731957
 itr = 1 throughput =3658739
 itr = 2 throughput =4150699
 itr = 3 throughput =4396580
 itr = 4 throughput =4220257
 itr = 5 throughput =3900271
 itr = 6 throughput =4225693
 itr = 7 throughput =4329937
 itr = 8 throughput =4382987
 itr = 9 throughput =4272161
# Throughput     GET : min= 3658739.0, max= 4396580.0, avg= 4126928.100 --> [3731957, 3658739, 4150699, 4396580, 4220257, 3900271, 4225693, 4329937, 4382987, 4272161]
```

#### Test 3. putGetBenchmarkTest
```
Aug 19, 2017 1:22:41 PM com.hazelcast.internal.partition.impl.PartitionStateManager
INFO: [192.168.1.5]:5701 [dev] [3.8.3] Initializing cluster partition table arrangement...
 itr = 0 throughput =897550
 itr = 1 throughput =879127
 itr = 2 throughput =1020598
 itr = 3 throughput =1195435
 itr = 4 throughput =1156474
 itr = 5 throughput =1268886
 itr = 6 throughput =1184997
 itr = 7 throughput =1011782
 itr = 8 throughput =1109120
 itr = 9 throughput =1119443
# Throughput PutGet5050 : min= 879127.0, max= 1268886.0, avg= 1084341.200 --> [897550, 879127, 1020598, 1195435, 1156474, 1268886, 1184997, 1011782, 1109120, 1119443]
```

### Benchmark Apache Geode/ Gemfire Map

#### Test-1. putBenchmarkTest
```
[warn 2017/08/19 13:13:12.323 IST <main> tid=0x1] Region geode-map is being created with scope DISTRIBUTED_NO_ACK but enable-network-partition-detection is enabled in the distributed system.  This can lead to cache inconsistencies if there is a network failure.

 itr = 0 throughput =463168
 itr = 1 throughput =491695
 itr = 2 throughput =665171
 itr = 3 throughput =596012
 itr = 4 throughput =682790
 itr = 5 throughput =698291
 itr = 6 throughput =639445
 itr = 7 throughput =637996
 itr = 8 throughput =718585
 itr = 9 throughput =581336
# Throughput     Put : min= 463168.0, max= 718585.0, avg= 617448.900 --> [463168, 491695, 665171, 596012, 682790, 698291, 639445, 637996, 718585, 581336]
[info 2017/08/19 13:15:08.492 IST <Distributed system shutdown hook> tid=0xc] VM is exiting - shutting down distributed system
```

#### Test-2.getBenchmarkTest
```
[warn 2017/08/19 13:16:10.680 IST <main> tid=0x1] Region geode-map is being created with scope DISTRIBUTED_NO_ACK but enable-network-partition-detection is enabled in the distributed system.  This can lead to cache inconsistencies if there is a network failure.

 itr = 0 throughput =3820057
 itr = 1 throughput =4104837
 itr = 2 throughput =4331690
 itr = 3 throughput =4046099
 itr = 4 throughput =4378970
 itr = 5 throughput =4050795
 itr = 6 throughput =4065424
 itr = 7 throughput =4311689
 itr = 8 throughput =4176658
 itr = 9 throughput =4175231
# Throughput     GET : min= 3820057.0, max= 4378970.0, avg= 4146145.000 --> [3820057, 4104837, 4331690, 4046099, 4378970, 4050795, 4065424, 4311689, 4176658, 4175231]
[info 2017/08/19 13:16:46.045 IST <Distributed system shutdown hook> tid=0xc] VM is exiting - shutting down distributed system
```

#### Test-3.putgetBenchmarkTest
```
[warn 2017/08/19 13:17:30.412 IST <main> tid=0x1] Region geode-map is being created with scope DISTRIBUTED_NO_ACK but enable-network-partition-detection is enabled in the distributed system.  This can lead to cache inconsistencies if there is a network failure.

 itr = 0 throughput =841377
 itr = 1 throughput =963022
 itr = 2 throughput =968162
 itr = 3 throughput =970787
 itr = 4 throughput =889093
 itr = 5 throughput =1081982
 itr = 6 throughput =1065583
 itr = 7 throughput =936503
 itr = 8 throughput =1224659
 itr = 9 throughput =1107132
# Throughput PutGet5050 : min= 841377.0, max= 1224659.0, avg= 1004830.000 --> [841377, 963022, 968162, 970787, 889093, 1081982, 1065583, 936503, 1224659, 1107132]
[info 2017/08/19 13:19:05.444 IST <Distributed system shutdown hook> tid=0xc] VM is exiting - shutting down distributed system
```