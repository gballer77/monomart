export function mockFetch(response: any = {}) {
  window.fetch = jest.fn().mockReturnValue(Promise.resolve({
    json: () => response
  }));
}
