import { useCallback, useEffect, useState } from "react";

const useAsync = (asyncFunction, immediate = true) => {
  const [isLoading, setIsLoding] = useState(false);
  const [value, setValue] = useState(null);
  const [error, setError] = useState(null);

  const execute = useCallback(() => {
    setIsLoding(true);
    return asyncFunction()
      .then((response) => setValue(response))
      .catch((err) => setError(err))
      .finally(() => setIsLoding(false));
  }, [asyncFunction]);

  useEffect(() => {
    if (immediate) execute();
  }, [execute, immediate]);

  return { value, isLoading, error };
};
