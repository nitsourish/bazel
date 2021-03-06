// Copyright 2014 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.analysis;

import static com.google.devtools.build.lib.pkgcache.PackageManager.PackageManagerStatistics;

import com.google.common.collect.ImmutableList;
import java.util.Collection;

/**
 * This event is fired after the analysis phase is complete.
 */
public class AnalysisPhaseCompleteEvent {

  private final Collection<ConfiguredTarget> topLevelTargets;
  private final long timeInMs;
  private int targetsVisited;
  private final PackageManagerStatistics pkgManagerStats;
  private final int actionsConstructed;

  /**
   * Construct the event.
   *
   * @param topLevelTargets The set of active topLevelTargets that remain.
   */
  public AnalysisPhaseCompleteEvent(
      Collection<? extends ConfiguredTarget> topLevelTargets,
      int targetsVisited,
      long timeInMs,
      PackageManagerStatistics pkgManagerStats,
      int actionsConstructed) {
    this.timeInMs = timeInMs;
    this.topLevelTargets = ImmutableList.copyOf(topLevelTargets);
    this.targetsVisited = targetsVisited;
    this.pkgManagerStats = pkgManagerStats;
    this.actionsConstructed = actionsConstructed;
  }

  /**
   * @return The set of active topLevelTargets remaining, which is a subset
   *     of the topLevelTargets we attempted to analyze.
   */
  public Collection<ConfiguredTarget> getTopLevelTargets() {
    return topLevelTargets;
  }

  /**
   * @return The number of topLevelTargets freshly visited during analysis
   */
  public int getTargetsVisited() {
    return targetsVisited;
  }

  public long getTimeInMs() {
    return timeInMs;
  }

  public int getActionsConstructed() {
    return actionsConstructed;
  }

  /**
   * Returns package manager statistics.
   */
  public PackageManagerStatistics getPkgManagerStats() {
    return pkgManagerStats;
  }
}
