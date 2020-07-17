import React, { memo } from "react";
import Lottie from "react-lottie";
import animationData from "../lotties/lottie-loading.json";
const style = {
  margin: "3O px",
};

const defaultOptions = {
  loop: true,
  autoplay: true,
  animationData: animationData,
  rendererSettings: {
    preserveAspectRatio: "xMidYMid slice",
  },
};
export default memo((options = {}, clickToPause = false) => {
  const mixedOptions = {
    ...defaultOptions,
    ...options,
  };
  return (
    <>
      <Lottie
        options={mixedOptions}
        height={50}
        width={50}
        isClickToPauseDisabled={!!clickToPause}
      />
    </>
  );
});
