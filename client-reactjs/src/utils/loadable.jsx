import React, { lazy, Suspense } from "react";

// importFunction => () => Promise
const loadable = (importFunc, fallback) => {
  const LazyComp = lazy(importFunc);
  return (props) => (
    <Suspense fallback={fallback}>
      <LazyComponent {...props} />
    </Suspense>
  );
};

export default loadable;
