import React, { memo } from "react";
import Lottie from "react-lottie";
import animationData from "../../lotties/not-found.json";

const defaultOptions = {
  loop: true,
  autoplay: true,
  animationData: animationData,
  rendererSettings: {
    preserveAspectRatio: "xMidYMid slice",
  },
};
const Notfound = memo((options = {}, clickToPause = false) => {
  const mixedOptions = {
    ...defaultOptions,
    ...options,
  };
  return (
    <div className="container">
      <Lottie options={mixedOptions} isClickToPauseDisabled={!!clickToPause} />
    </div>
  );
});

export default {
  path: "*",
  component: Notfound,
  name: "not-found",
};
