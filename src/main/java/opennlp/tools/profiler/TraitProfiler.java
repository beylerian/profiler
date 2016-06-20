package opennlp.tools.profiler;

/**
 * Created by anthony on 6/20/16.
 */
public interface TraitProfiler extends Profiler{
  // Enum of all types of personality traits
  enum TRAITS {
    TRAIT_EXTROVERT, TRAIT_STABLE, TRAIT_AGREEABLE, TRAIT_CONSCIENTIOUS, TRAIT_OPEN
  }

  /**
   * @param sample
   * @return TRAITS based on the classification based on one sample
   */
  Double[] traitify(ProfilerSample sample);
}
