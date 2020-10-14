/*
 * Copyright (C) 2020, K2N.IO.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

package com.gocypher.cybench.jmh.jvm.test;

import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import com.gocypher.cybench.jmh.jvm.client.tests.StringBenchmarks;

public class BenchmarksTest {

    public static void main (String []args)throws Exception{

        // Number of separate full executions of a benchmark (warm up+measurement), this is returned still as one primary score item
        int forks = 1 ;
        //Number of measurements per benchmark operation, this is returned still as one primary score item
        int measurementIterations = 3 ;
        // number of iterations executed for warm up
        int warmUpIterations = 1 ;
        // number of seconds dedicated for each warm up iteration
        int warmUpSeconds = 5 ;
        // number of threads for benchmark test execution
        int threads =1;

        OptionsBuilder optBuild = new OptionsBuilder();

        optBuild.include(StringBenchmarks.class.getSimpleName());
        //optBuild.include(NumberBenchmarks.class.getSimpleName());
        //optBuild.include(IOSyncFileSeekBenchmarks.class.getSimpleName());
        //optBuild.include(IOAsyncAPIComparisonBenchmarks.class.getSimpleName());
        //optBuild.include(NumberBenchmarks.class.getSimpleName());

        Options opt = optBuild
                .forks(forks)
                .measurementIterations(measurementIterations)
                //.measurementTime(TimeValue.seconds(20))
                .warmupIterations(warmUpIterations)
                .warmupTime(TimeValue.seconds(warmUpSeconds))
                .threads(threads)
                .shouldDoGC(true)
                //.addProfiler(GCProfiler.class)
                .addProfiler(StackProfiler.class) //produces zeros
                //.addProfiler(HotspotMemoryProfiler.class)
                //.addProfiler(HotspotRuntimeProfiler.class)
                //.addProfiler(HotspotClassloadingProfiler.class)
                //.addProfiler(HotspotCompilationProfiler.class)
                //.addProfiler(HotspotThreadProfiler.class)
                //.addProfiler(JavaFlightRecorderProfiler.class) //throws errors, requires -XX:+UnlockCommercialFeatures, deprecated in java 13
                //.addProfiler(AsyncProfiler.class) //Unable to load async-profiler. Ensure asyncProfiler library is on LD_LIBRARY_PATH, -Djava.library.path or libPath=
                //.addProfiler(ClassloaderProfiler.class)
                //.addProfiler(CompilerProfiler.class)
                //.addProfiler(DTraceAsmProfiler.class)//Cannot run program "sudo": CreateProcess error=2, The system cannot find the file specified
                //.addProfiler(PausesProfiler.class)
                //.addProfiler(SafepointsProfiler.class)
                //.addProfiler(StackProfiler.class)
                //.addProfiler(WinPerfAsmProfiler.class)

                .build();

        /*Runner runner = new Runner(opt);
        Collection<RunResult> results = runner.run() ;

        LOG.info ("Tests finished, executed tests count:{}",results.size()) ;
        System.out.println("Thread count:"+results.iterator().next().getParams().getThreads());
        IOUtils.removeTestDataFiles() ;
        LOG.info("Test data files removed!!!");

        Iterator<RunResult> it = results.iterator() ;
        while (it.hasNext()){
            RunResult result = it.next() ;

            Double score = null ;
            if (result.getPrimaryResult().getLabel().equalsIgnoreCase("stringConcatMultiChars")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("stringBufferConcatMultiChars")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("stringReplaceAll")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("stringBufferReplaceAll")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("findRegexCompiled")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("findRegexUnCompiled")
                    ){
                score = new StringBufferScoreConverter().convertScore(result.getPrimaryResult().getScore(),null) ;

            }
            else if (
                    result.getPrimaryResult().getLabel().equalsIgnoreCase("generateAndAddDoubleNumbers")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("generateAndAddAtomicNumbers")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("generateAndAddBigDecimalNumbers")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("generateAndLogarithmDoubleNumbers")
                    ||result.getPrimaryResult().getLabel().equalsIgnoreCase("generateAndPowerDoubleNumbers")
                    ){
                score = new NumbersScoreConverter().convertScore(result.getPrimaryResult().getScore(),null)  ;
            }
            else {
                score = result.getPrimaryResult().getScore() ;
            }


            System.out.println(result.getPrimaryResult().getLabel() + ":"+score);

            System.out.println("Extra results for test:");
            for (Result secondaryResult:result.getSecondaryResults().values()){
                System.out.println(secondaryResult.getLabel().replaceAll("·","")+" Score:"+secondaryResult.getScore()+" Units:"+secondaryResult.getScoreUnit()+" Extr:"+secondaryResult.extendedInfo());
            }

        }
        */
        /*String label1 = "for a real test, make this big and this big,for a real test, make this big and this big,for a real test, make this big and this big";
        String label2 = "so that we can see GC effects,so that we can see GC effects,so that we can see GC effects" ;

        StringBuffer stringBuffer = new StringBuffer() ;
        long t1 = System.currentTimeMillis() ;
        for (int i = 0; i < 1000000; i++) {
            stringBuffer.append(label1);
            stringBuffer.append(i);
            stringBuffer.append(label2);
            stringBuffer.append(i);
        }
        long t2 = System.currentTimeMillis() ;
        System.out.println((t2-t1)+" " +stringBuffer.length());
        */
        //scanForBenchmarks() ;
    }
    /*private static void scanForBenchmarks () throws  Exception{
        Reflections reflections = new Reflections("", new SubTypesScanner(false));
        Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
        for (Class classObj :allClasses){
                if (classObj.isAnnotationPresent(State.class) && !classObj.getName().contains("jmh_generated")) {
                    System.out.println(classObj.getName() + "=>" + classObj.isAnnotationPresent(State.class) + ";");
                }

        }


    }
    */
}
