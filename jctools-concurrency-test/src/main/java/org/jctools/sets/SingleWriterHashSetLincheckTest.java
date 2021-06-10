package org.jctools.sets;

import org.jetbrains.kotlinx.lincheck.LinChecker;
import org.jetbrains.kotlinx.lincheck.annotations.Operation;
import org.jetbrains.kotlinx.lincheck.annotations.Param;
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen;
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions;
import org.junit.jupiter.api.Test;

@Param(name = "value", gen = IntGen.class, conf = "1:10")
public class SingleWriterHashSetLincheckTest {

    private final SingleWriterHashSet<Integer> set = new SingleWriterHashSet<>(16);

    @Operation
    public boolean add(@Param(name = "value") int key)
    {
        return set.add(key);
    }

    @Operation
    public boolean remove(@Param(name = "value") long key)
    {
        return set.remove(key);
    }

    @Operation
    public boolean contains(@Param(name = "value") long key)
    {
        return set.contains(key);
    }

    @Test
    public void stressTest()
    {
        StressOptions options = new StressOptions();
        new LinChecker(this.getClass(), options).check();
    }
}
